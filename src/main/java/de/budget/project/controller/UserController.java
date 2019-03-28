package de.budget.project.controller;

import de.budget.project.model.user.User;
import de.budget.project.model.user.UserWebRequest;
import de.budget.project.model.user.UserWebResponse;
import de.budget.project.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserService userService;

    @PostMapping("/users")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody UserWebRequest userWebRequest) {
        return userService.createUser(convertToEntity(userWebRequest)).getId();
    }

    @GetMapping("/users/{id}")
    public UserWebResponse getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users/email/{email}")
    public UserWebResponse findUserByEmail(@PathVariable("email") String email) {
        return convertToWebResponse(userService.getUserByEmail(email));
    }

    private User convertToEntity(UserWebRequest userWebRequest) {
        return modelMapper.map(userWebRequest, User.class);
    }

    private UserWebRequest convertToWebRequest(User user) {
        return modelMapper.map(user, UserWebRequest.class);
    }

    private UserWebResponse convertToWebResponse(User user) {
        return modelMapper.map(user, UserWebResponse.class);
    }
}