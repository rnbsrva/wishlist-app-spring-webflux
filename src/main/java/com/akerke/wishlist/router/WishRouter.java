package com.akerke.wishlist.router;

import com.akerke.wishlist.handler.WishHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class WishRouter {

    @Bean
    public RouterFunction<ServerResponse> wishRoutes(WishHandler wishHandler) {
        return route()
                .POST("/wish", wishHandler::create)
                .GET("/wish", wishHandler::getAll)
                .GET("/wish/{id}", wishHandler::getById)
                .PATCH("/wish/{id}", wishHandler::update)
                .DELETE("/wish/{id}", wishHandler::delete)
                .build();
    }
}
