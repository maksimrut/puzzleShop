package com.rutkouski.puzzleshop.model.mapper;

import com.rutkouski.puzzleshop.model.entity.AbstractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface RowMapper<E extends AbstractEntity> {
    Optional<E> mapRow(ResultSet resultSet);

    default List<E> mapRows(ResultSet resultSet) throws SQLException {
        List<E> entities = new ArrayList<>();
        while(resultSet.next()) {
            Optional<E> entity = mapRow(resultSet);
            if (entity.isPresent()) {
                entities.add(entity.get());
            }
        }
        return entities;
    }
}
