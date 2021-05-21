package com.filos.spring.web;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.filos.entity.User;
import com.filos.requests.LoginRequests;
import com.filos.user.CreateUser;
import com.filos.user.FindUser;
import com.filos.user.UserLogin;

@RestController
public class UserController {

    private final CreateUser createUser;
    private final UserLogin userLogin;
    private final FindUser findUser;

    public UserController(CreateUser createUser, UserLogin userLogin, FindUser findUser) {
        this.createUser = createUser;
        this.userLogin = userLogin;
        this.findUser = findUser;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User createUser(@RequestBody final User userWeb) {
        return createUser.create(userWeb);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public User login(@RequestBody LoginRequests loginRequests) {
        return userLogin.login(loginRequests);
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable("userId") final String userId) {
        return findUser.findById(userId);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> allUsers() {
        return findUser.findAllUsers();
    }
}