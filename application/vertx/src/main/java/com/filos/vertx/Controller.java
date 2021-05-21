package com.filos.vertx;

import java.util.stream.Collectors;

import com.filos.configs.VertxConfig;
import com.filos.entity.User;
import com.filos.requests.LoginRequests;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class Controller {
    private final VertxConfig config = new VertxConfig();

    public void createUser(final RoutingContext routingContext) {
        var response = routingContext.response();
        var body = routingContext.getBody();
        if (isNull(body)) {
            sendError(400, response);
        } else {
            var userWeb = body.toJsonObject()
                              .mapTo(User.class);
            var user = config.createUser()
                             .create(userWeb);
            var result = JsonObject.mapFrom(user);
            sendSuccess(result, response);
        }
    }

    public void login(final RoutingContext routingContext) {
        var response = routingContext.response();
        var email = routingContext.request()
                                  .getParam("email");
        var password = routingContext.request()
                                     .getParam("password");
        if (email == null || password == null) {
            sendError(400, response);
        } else {
            var user = config.loginUser()
                             .login(new LoginRequests(email, password));
            var result = JsonObject.mapFrom(user);
            sendSuccess(result, response);
        }
    }

    public void findUser(final RoutingContext routingContext) {
        var response = routingContext.response();
        var userId = routingContext.request()
                                   .getParam("userId");
        if (userId == null) {
            sendError(400, response);
        } else {
            try {
                var user = config.findUser()
                                 .findById(userId);
                var result = JsonObject.mapFrom(user);
                sendSuccess(result, response);
            } catch (RuntimeException e) {
                sendError(404, response);
            }
        }
    }

    public void findAllUser(final RoutingContext routingContext) {
        var response = routingContext.response();
        var users = config.findUser()
                          .findAllUsers();
        var result = users.stream()
                          .map(JsonObject::mapFrom)
                          .collect(Collectors.toList());
        response.putHeader("content-type", "application/json")
                .end(result.toString());
    }

    private boolean isNull(final Buffer buffer) {
        return buffer == null || "".equals(buffer.toString());
    }

    private void sendError(int statusCode, HttpServerResponse response) {
        response.putHeader("content-type", "application/json")
                .setStatusCode(statusCode)
                .end();
    }

    private void sendSuccess(JsonObject body, HttpServerResponse response) {
        response.putHeader("content-type", "application/json")
                .end(body.encodePrettily());
    }
}
