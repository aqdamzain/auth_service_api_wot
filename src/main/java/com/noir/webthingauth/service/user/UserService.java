package com.noir.webthingauth.service.user;

import com.noir.webthingauth.domain.User;
import com.noir.webthingauth.exceptions.UlAuthException;

public interface UserService {
    User validateUser(String username, String password) throws UlAuthException;

    User getUserById(Integer userId) throws UlAuthException;

    User registerUser(String username, String password) throws UlAuthException;
}
