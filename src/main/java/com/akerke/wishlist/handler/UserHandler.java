package com.akerke.wishlist.handler;

import com.akerke.wishlist.dto.UserDTO;
import com.akerke.wishlist.entity.User;
import com.akerke.wishlist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

@RequiredArgsConstructor
@Component
public class UserHandler {

    private final UserService userService;

    public Mono<ServerResponse> getAll(ServerRequest request){
        Flux<User> users = userService.findAll();
        return ok().contentType(APPLICATION_JSON).body(users, User.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request){
        String userId = String.valueOf(request.pathVariable("id"));
        return userService.findById(userId).flatMap(person -> ok().contentType(APPLICATION_JSON).bodyValue(person))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<UserDTO> userDTOMono = request.bodyToMono(UserDTO.class);
        return ok().body(userDTOMono.flatMap(userService::create), User.class);
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        String userId = request.pathVariable("id");
        return request.bodyToMono(UserDTO.class)
                .flatMap(userDTO ->
                        userService.update(userId, userDTO)
                                .flatMap(user ->
                                        ok().contentType(MediaType.APPLICATION_JSON).bodyValue(user))
                                .switchIfEmpty(notFound().build())
                );
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String userId = request.pathVariable("id");

        return userService.delete(userId)
                .then(noContent().build())
                .switchIfEmpty(notFound().build());
    }


}
