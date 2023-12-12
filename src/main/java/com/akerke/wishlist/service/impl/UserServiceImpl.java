package com.akerke.wishlist.service.impl;

import com.akerke.wishlist.dto.UserDTO;
import com.akerke.wishlist.entity.User;
import com.akerke.wishlist.exception.EntityNotFoundException;
import com.akerke.wishlist.mapper.UserMapper;
import com.akerke.wishlist.repository.UserRepository;
import com.akerke.wishlist.repository.WishRepository;
import com.akerke.wishlist.service.UserService;
import com.akerke.wishlist.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final WishRepository wishService;

    @Override
    public Mono<User> create(UserDTO userDTO) {
        return userRepository.save(userMapper.toModel(userDTO));
    }

    @Override
    public Mono<User> findById(String id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException(User.class, id)))
                .flatMap(user -> enrichWish(Mono.just(user)));
    }

    @Override
    public Flux<User> findAll() {
        return userRepository.findAll()
                .flatMap(user -> enrichWish(Mono.just(user)));
    }

    @Override
    public Mono<User> update(String id, UserDTO userDTO) {
        return this.findById(id)
                .flatMap(user -> {
                    userMapper.update(userDTO, user);
                    return userRepository.save(user);
                })
                .flatMap(user -> enrichWish(Mono.just(user)));
    }

    @Override
    public Mono<Void> delete(String id) {
        return findById(id)
                .flatMap(user -> userRepository.delete(user).then());
    }

    private Mono<User> enrichWish(Mono<User> user) {
        return user.flatMap(u ->
                wishService.findAllByUserId(u.getId())
                        .collectList()
                        .map(wishList -> {
                            u.setWishList(wishList);
                            return u;
                        })
        );
    }
}
