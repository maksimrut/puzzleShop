package com.rutkouski.puzzleshop.model.mapper.impl;

import com.rutkouski.puzzleshop.model.entity.User;
import com.rutkouski.puzzleshop.model.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.rutkouski.puzzleshop.model.mapper.ColumnName.*;

public class UserMapper implements RowMapper<User> {

    @Override
    public Optional<User> mapRow(ResultSet resultSet) {

        User user = new User();
        try {
            user.setId(resultSet.getInt(USER_ID));
            user.setLogin(resultSet.getString(LOGIN));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setEmail(resultSet.getString(EMAIL));
            user.setFirstName(resultSet.getString(FIRST_NAME));
            user.setPhoneNumber(resultSet.getString(PHONE));
            user.setRole(User.Role.valueOf(resultSet.getString(USER_ROLE).toUpperCase()));
            user.setStatus(User.Status.valueOf(resultSet.getString(USER_STATUS).toUpperCase()));
            return Optional.of(user);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}
