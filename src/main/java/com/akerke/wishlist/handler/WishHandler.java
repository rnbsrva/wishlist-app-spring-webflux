package com.akerke.wishlist.handler;

import com.akerke.wishlist.dto.WishDTO;
import com.akerke.wishlist.entity.Wish;
import com.akerke.wishlist.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
@RequiredArgsConstructor
public class WishHandler {

    private final WishService wishService;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        Flux<Wish> wishes = wishService.findAll();
        return ok().contentType(MediaType.APPLICATION_JSON).body(wishes, Wish.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        String wishId = request.pathVariable("id");
        return wishService.findById(wishId)
                .flatMap(wish -> ok().contentType(MediaType.APPLICATION_JSON).bodyValue(wish))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<WishDTO> wishDTOMono = request.bodyToMono(WishDTO.class);
        return ok().body(wishDTOMono.flatMap(wishService::create), Wish.class);
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        String wishId = request.pathVariable("id");
        return request.bodyToMono(WishDTO.class)
                .flatMap(wishDTO ->
                        wishService.update(wishId, wishDTO)
                                .flatMap(wish ->
                                        ok().contentType(MediaType.APPLICATION_JSON).bodyValue(wish))
                                .switchIfEmpty(notFound().build())
                );
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String wishId = request.pathVariable("id");

        return wishService.delete(wishId)
                .then(noContent().build())
                .switchIfEmpty(notFound().build());
    }
}
