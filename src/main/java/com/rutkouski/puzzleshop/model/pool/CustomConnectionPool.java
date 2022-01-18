package com.rutkouski.puzzleshop.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import static com.rutkouski.puzzleshop.model.pool.ConnectionFactory.POOL_SIZE;

public class CustomConnectionPool {
    static Logger logger = LogManager.getLogger();

    private static final int ONE = 1;
    private static final long THREADS_FINISH_DELAY = 100;
    private static final int DELAY_BEFORE_CONNECTION_NUMBER_CHECK = 1;
    private static final int PERIOD_BETWEEN_CONNECTION_NUMBER_CHECK = 1;

    private static CustomConnectionPool instance;
    private static ReentrantLock poolLock = new ReentrantLock();
    private static AtomicBoolean create = new AtomicBoolean(false);
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> givenAwayConnections;

    private Timer poolCheck;
    private CountDownLatch connectionsCheckLatch;
    private AtomicBoolean connectionsNumberCheck = new AtomicBoolean(false);

    private CustomConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
        givenAwayConnections = new LinkedBlockingDeque<>(POOL_SIZE);

        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                ProxyConnection connection = new ProxyConnection(ConnectionFactory.createConnection());
                boolean isAdded = freeConnections.offer(connection);
                logger.debug("New connection has added to freeConnections: {}", isAdded);
            } catch (SQLException e) {
                logger.error("New connection has not created! ", e);
            }
        }
        if (freeConnections.isEmpty()) {
            logger.fatal("Can not create connection to database!");
            throw new RuntimeException("Can not create database connection!");
        }
        checkPoolSizeTimer();
        logger.info("Connection pool was created");
    }

    public static CustomConnectionPool getInstance() {
        if (!create.get()) {
            try {
                poolLock.lock();
                if (instance == null) {
                    instance = new CustomConnectionPool();
                    create.set(true);
                }
            } finally {
                poolLock.unlock();
            }
        }
        return instance;
    }

    public Connection takeConnection() {
        ProxyConnection connection = null;
        try {
            if (connectionsNumberCheck.get()) {
                connectionsCheckLatch.await();
            }
            connection = freeConnections.take();
            givenAwayConnections.put(connection);
            logger.debug("Connection was given to user");
        } catch (InterruptedException e) {
            logger.error("InterruptedException occured in the takeConnection method ", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public boolean releaseConnection(Connection connection) {

        boolean result = false;
        if (connection.getClass() != ProxyConnection.class) {
            logger.warn("Illegal connection in releaseConnection method: {}", connection);
            return false;
        }
        try {
            if (connectionsNumberCheck.get()) {
                connectionsCheckLatch.await();
            }
            ProxyConnection proxyConnection = (ProxyConnection) connection;
            if (givenAwayConnections.remove(proxyConnection)) {
                freeConnections.put(proxyConnection);
                result = true;
                logger.debug("Connection was returned to connection pool");
            }
        } catch (InterruptedException e) {
            logger.error("InterruptedException occured in the releaseConnection method ", e);
            Thread.currentThread().interrupt();
        }
        return result;
    }

    public void destroyPool() {
        poolCheck.cancel();
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
                logger.debug("Connection was closed in destroyPool method");
            } catch (InterruptedException e) {
                logger.error("InterruptedException occured in the destroyPool method", e);
            } catch (SQLException e) {
                logger.error("Unable to close connection", e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
                logger.debug("Database drivers were deregistered");
            } catch (SQLException e) {
                logger.error("SQLException in the deregisterDrivers method ", e);
            }
        });
    }

    private void checkPoolSizeTimer() {
        poolCheck = new Timer();
        poolCheck.schedule(new TimerTask() {

                               @Override
                               public void run() {
                                   int connectionsNumber = calculateConnectionsNumber();
                                   if (connectionsNumber < POOL_SIZE) {
                                       int requiredConnections = POOL_SIZE - connectionsNumber;
                                       for (int i = 0; i < requiredConnections; i++) {
                                           try {
                                               Connection connection = ConnectionFactory.createConnection();
                                               boolean isAdded = freeConnections.offer((ProxyConnection) connection);
                                               logger.info("New connection has added to freeConnections: {}", isAdded);
                                           } catch (SQLException e) {
                                               logger.error("New connection was not created!", e);
                                           }
                                       }
                                   }
                               }
                           }
                , TimeUnit.HOURS.toMillis(DELAY_BEFORE_CONNECTION_NUMBER_CHECK)
                , TimeUnit.HOURS.toMillis(PERIOD_BETWEEN_CONNECTION_NUMBER_CHECK));
    }

    private int calculateConnectionsNumber() {
        int currentConnectionsNumber = POOL_SIZE;
        try {
            connectionsCheckLatch = new CountDownLatch(ONE);
            connectionsNumberCheck.set(true);
            TimeUnit.MILLISECONDS.sleep(THREADS_FINISH_DELAY);
            currentConnectionsNumber = freeConnections.size() + givenAwayConnections.size();
        } catch (InterruptedException e) {
            logger.error("Thread was interrupted while connection check", e);
        } finally {
            connectionsNumberCheck.set(false);
            connectionsCheckLatch.countDown();
        }
        return currentConnectionsNumber;
    }
}
