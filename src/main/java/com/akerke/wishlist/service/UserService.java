package com.akerke.wishlist.service;

import com.akerke.wishlist.dto.UserDTO;
import com.akerke.wishlist.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> create(UserDTO userDTO);

    Mono<User> findById(String id);

    Flux<User> findAll();

    Mono<User> update(String id, UserDTO userDTO);

    Mono<Void> delete(String id);
}
