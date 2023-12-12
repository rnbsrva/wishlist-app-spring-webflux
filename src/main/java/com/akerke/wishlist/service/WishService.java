package com.akerke.wishlist.service;

import com.akerke.wishlist.dto.WishDTO;
import com.akerke.wishlist.entity.Wish;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WishService {

    Mono<Wish> create(WishDTO wishDTO);

    Mono<Wish> findById(String id);

    Flux<Wish> findAll();

    Mono<Wish> update(String id, WishDTO wishDTO);

    Mono<Void> delete(String id);

    Flux<Wish> findByUserId(String userId);
}
