package com.akerke.wishlist.service.impl;

import com.akerke.wishlist.dto.WishDTO;
import com.akerke.wishlist.entity.Wish;
import com.akerke.wishlist.exception.EntityNotFoundException;
import com.akerke.wishlist.mapper.WishMapper;
import com.akerke.wishlist.repository.UserRepository;
import com.akerke.wishlist.repository.WishRepository;
import com.akerke.wishlist.service.UserService;
import com.akerke.wishlist.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;
    private final UserService userService;
    private final WishMapper wishMapper;
    private final UserRepository userRepository;

    @Override
    public Mono<Wish> create(WishDTO wishDTO) {
        return userService.findById(wishDTO.userId())
                .flatMap(user -> {
                    Wish wish = wishMapper.toModel(wishDTO);
                    wish.setUserId(wishDTO.userId());
                    if(user.getWishList()!=null){
                        user.getWishList().add(wish);
                    }else{
                        user.setWishList(new ArrayList<>());
                        user.getWishList().add(wish);
                    }
                    userRepository.save(user);
                    return wishRepository.save(wish);
                });
    }

    @Override
    public Mono<Wish> findById(String id) {
        return wishRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException(Wish.class, id)));
    }

    @Override
    public Flux<Wish> findAll() {
        return wishRepository.findAll();
    }

    @Override
    public Mono<Wish> update(String id, WishDTO wishDTO) {
        return this.findById(id)
                .flatMap(wish -> {
                    wishMapper.update(wishDTO, wish);
                    return wishRepository.save(wish);
                });
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.findById(id)
                .flatMap(wish -> wishRepository.delete(wish).then());
    }

    @Override
    public Flux<Wish> findByUserId(String userId) {
        return wishRepository.findAllByUserId(userId);
    }
}
