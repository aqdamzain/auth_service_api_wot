package com.noir.webthingauth.repositories.user;

import com.noir.webthingauth.domain.User;
import com.noir.webthingauth.exceptions.UlAuthException;

public interface UserRepository {
    Integer create(String username, String password) throws UlAuthException;

    User findByUsernameAndPassword(String username, String password) throws UlAuthException;

    Integer getCountByUsername(String username);

    User findById(Integer userId);
}
