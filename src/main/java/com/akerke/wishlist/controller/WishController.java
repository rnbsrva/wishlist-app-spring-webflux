package com.akerke.wishlist.controller;

import com.akerke.wishlist.dto.UserDTO;
import com.akerke.wishlist.dto.WishDTO;
import com.akerke.wishlist.entity.User;
import com.akerke.wishlist.entity.Wish;
import com.akerke.wishlist.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("wish")
public class WishController {

    private final WishService wishService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Wish> create(@RequestBody WishDTO wishDTO) {
        return wishService.create(wishDTO);

    }

    @GetMapping
    Flux<Wish> getAll(){
        return wishService.findAll();
    }

    @PatchMapping("{id}")
    public Mono<Wish> update(
            @RequestBody WishDTO wishDTO,
            @PathVariable String id
            ){
        return wishService.update(id, wishDTO);
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Wish>> getById(
            @PathVariable String id
    ){
        return this.wishService.findById(id)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable("id") String id
    ) {
        return wishService.delete(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

}
