package com.rutkouski.puzzleshop.model.dao;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

/**
 * @param <K> the type parameter
 * @param <T> the type parameter
 * @author Maksim Rutkouski
 * <p>
 * The interface Base dao. The parent of other dao interfaces.
 * Provides access to the database
 */
public interface BaseDao<K, T extends AbstractEntity> {

    int FIRST_PARAM_INDEX = 1;
    int SECOND_PARAM_INDEX = 2;
    int THIRD_PARAM_INDEX = 3;
    int FOURTH_PARAM_INDEX = 4;
    int FIFTH_PARAM_INDEX = 5;
    int SIXTH_PARAM_INDEX = 6;
    int SEVENTH_PARAM_INDEX = 7;

    /**
     * Finds all entities
     *
     * @return the list of entities
     * @throws DaoException happens if the request to database could not be handled
     */
    List<T> findAll() throws DaoException;

    /**
     * Finds entity by id
     *
     * @param id the entity's id
     * @return the optional entity
     * @throws DaoException happens if the request to database could not be handled
     */
    Optional<T> findById(K id) throws DaoException;

    /**
     * Deletes entity by id
     *
     * @param id the entity's id
     * @return the boolean result
     * @throws DaoException happens if the request to database could not be handled
     */
    boolean deleteById(K id) throws DaoException;

    /**
     * Deletes entity
     *
     * @param entity the entity to delete
     * @return the boolean result
     * @throws DaoException happens if the request to database could not be handled
     */
    boolean delete(T entity) throws DaoException;

    /**
     * Creates entity
     *
     * @param entity the entity to insert into the database
     * @return the created entity
     * @throws DaoException happens if the request to database could not be handled
     */
    T create(T entity) throws DaoException;
}
