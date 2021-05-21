package com.filos.manual;

import com.filos.configs.ManualConfig;
import com.filos.entity.User;
import com.filos.requests.LoginRequests;

public class AppMain {
    public static void main(String[] args) {
        var config = new ManualConfig();
        var createUser = config.createUser();
        var findUser = config.findUser();
        var loginUser = config.loginUser();
        var user = User.builder()
                       .email("test@test.com")
                       .password("somepassword")
                       .lastName("doe")
                       .firstName("john")
                       .build();

        // Create a user
        var actualCreateUser = createUser.create(user);
        System.out.println("User created with id " + actualCreateUser.getId());

        // Find a user by id
        var actualFindUser = findUser.findById(actualCreateUser.getId());
        System.out.println("Found user with id " + actualFindUser.getId());

        // List all users
        var users = findUser.findAllUsers();
        System.out.println("List all users: " + users);

        // Login
        LoginRequests somepassword = new LoginRequests("test@test.com", "somepassword");
        loginUser.login(somepassword);
        System.out.println("Allowed to login with email '%s' and password  '%s'".formatted("test@test.com", "somepassword"));

    }
}
