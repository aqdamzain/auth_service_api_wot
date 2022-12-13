package com.noir.webthingauth.repositories.user;

import com.noir.webthingauth.domain.User;
import com.noir.webthingauth.exceptions.UlAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_CREATE = "INSERT INTO TB_USERS(USER_ID, USERNAME, PASSWORD) " +
            "VALUES(NEXTVAL('TB_USERS_SEQ'), ?, ?)";

    private static final String SQL_COUNT_BY_USERNAME = "SELECT COUNT(*) FROM " +
            "TB_USERS WHERE USERNAME = ?";

    private static final String SQL_FIND_BY_ID = "SELECT USER_ID, USERNAME, PASSWORD FROM " +
            "TB_USERS WHERE USER_ID = ?";

    private static final String SQL_FIND_BY_USERNAME = "SELECT USER_ID, USERNAME, PASSWORD FROM " +
            "TB_USERS WHERE USERNAME = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String username, String password) throws UlAuthException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, username);
                ps.setString(2, password);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("USER_ID");
        }catch (Exception e) {
            throw new UlAuthException("Invalid details. Failed to create account");
        }
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws UlAuthException {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_USERNAME, new Object[]{username}, userRowMapper);
            if(!password.equals(user.getPassword()))
                throw new UlAuthException("Invalid username/password");
            return user;
        }catch (EmptyResultDataAccessException e){
            throw new UlAuthException("Invalid username/password");
        }
    }

    @Override
    public Integer getCountByUsername(String username) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_USERNAME, new Object[]{username}, Integer.class);
    }

    @Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId}, userRowMapper);
    }

    private final RowMapper<User> userRowMapper = ( (rs, rowNum) -> {
        return new User(rs.getInt("USER_ID"),
                rs.getString("USERNAME"),
                rs.getString("PASSWORD"));
    });
}
