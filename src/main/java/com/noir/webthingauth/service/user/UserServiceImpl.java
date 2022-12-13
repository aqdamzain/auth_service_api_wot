package com.noir.webthingauth.service.user;

import com.noir.webthingauth.domain.User;
import com.noir.webthingauth.exceptions.UlAuthException;
import com.noir.webthingauth.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String username, String password) throws UlAuthException {
        if(username != null) username = username.toLowerCase();
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User getUserById(Integer userId) throws UlAuthException {
        return userRepository.findById(userId);
    }

    @Override
    public User registerUser(String username, String password) throws UlAuthException {
        if(username != null) username = username.toLowerCase();
        Integer count = userRepository.getCountByUsername(username);
        if(count > 0)
            throw new UlAuthException("Username already in use");
        Integer userId = userRepository.create(username, password);
        return userRepository.findById(userId);
    }
}
