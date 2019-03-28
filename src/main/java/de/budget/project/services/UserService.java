package de.budget.project.services;

import de.budget.project.model.user.User;
import de.budget.project.model.user.UserWebResponse;

public interface UserService {

    User createUser(User user);

    UserWebResponse getUserById(Long id);

    User getUserByEmail(String email);
}