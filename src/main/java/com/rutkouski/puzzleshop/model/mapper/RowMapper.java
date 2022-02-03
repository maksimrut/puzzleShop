package com.rutkouski.puzzleshop.model.mapper;

import com.rutkouski.puzzleshop.model.entity.AbstractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @param <E> the type parameter
 * @author Maksim Rutkouski
 * <p>
 * The interface Row mapper.
 */
public interface RowMapper<E extends AbstractEntity> {
    /**
     * Abstract method for single row mapping
     *
     * @param resultSet the result set of single row
     * @return the optional entity
     */
    Optional<E> mapRow(ResultSet resultSet);

    /**
     * The default method for mapping several entities to list
     *
     * @param resultSet the result set of several entities
     * @return the list of entities
     * @throws SQLException happens if the request to database could not be handled
     */
    default List<E> mapRows(ResultSet resultSet) throws SQLException {
        List<E> entities = new ArrayList<>();
        while (resultSet.next()) {
            Optional<E> entity = mapRow(resultSet);
            entity.ifPresent(entities::add);
        }
        return entities;
    }
}
