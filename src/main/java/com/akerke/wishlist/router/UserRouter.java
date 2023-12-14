package com.akerke.wishlist.router;

import com.akerke.wishlist.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> userRoutes(UserHandler userHandler) {
        return route()
                .POST("/user", userHandler::save)
                .GET("/user", userHandler::getAll)
                .GET("/user/{id}", userHandler::getById)
                .PATCH("/user/{id}", userHandler::update)
                .DELETE("/user/{id}", userHandler::delete)
                .build();
    }
}
