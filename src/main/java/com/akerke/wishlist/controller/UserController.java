package com.akerke.wishlist.controller;

import com.akerke.wishlist.dto.UserDTO;
import com.akerke.wishlist.dto.WishDTO;
import com.akerke.wishlist.entity.User;
import com.akerke.wishlist.entity.Wish;
import com.akerke.wishlist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @GetMapping
    Flux<User> getAll(){
        return userService.findAll();
    }

    @PatchMapping("{id}")
    public Mono<User> update(
            @RequestBody UserDTO userDTO,
            @PathVariable String id
    ){
        return userService.update(id, userDTO);
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<User>> getUserById(
            @PathVariable String id
    ){
        return this.userService.findById(id)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable("id") String id
    ) {
        return userService.delete(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

}
