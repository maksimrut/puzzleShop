package com.rutkouski.puzzleshop.model.dao;

import com.rutkouski.puzzleshop.exception.DaoException;
import com.rutkouski.puzzleshop.model.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

/**
 * The interface Base dao.
 *
 * @param <K> the type parameter
 * @param <T> the type parameter
 */
public interface BaseDao<K, T extends AbstractEntity> {

    static final int FIRST_PARAM_INDEX = 1;
    static final int SECOND_PARAM_INDEX = 2;
     static final int THIRD_PARAM_INDEX = 3;
     static final int FOURTH_PARAM_INDEX = 4;
    static final int FIFTH_PARAM_INDEX = 5;
     static final int SIXTH_PARAM_INDEX = 6;
    static final int SEVENTH_PARAM_INDEX = 7;
     static final int EIGHT_PARAM_INDEX = 8; //TODO check quantity
     static final int NINE_PARAM_INDEX = 9;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<T> findAll() throws DaoException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<T> findById(K id) throws DaoException;

    /**
     * Delete by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean deleteById(K id) throws DaoException;

    /**
     * Delete boolean.
     *
     * @param entity the entity
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean delete(T entity) throws DaoException;

    /**
     * Create int.
     *
     * @param entity the entity
     * @return the int
     * @throws DaoException the dao exception
     */
    T create(T entity) throws DaoException;
}
