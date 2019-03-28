package de.budget.project.services.impl;

import de.budget.project.model.user.User;
import de.budget.project.model.user.UserWebResponse;
import de.budget.project.repository.UserRepository;
import de.budget.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserWebResponse getUserById(Long id) {
        UserWebResponse userById = userRepository.getUserById(id);
        return userById;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}