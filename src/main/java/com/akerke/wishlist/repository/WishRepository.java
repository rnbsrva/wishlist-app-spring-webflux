package com.akerke.wishlist.repository;

import com.akerke.wishlist.entity.Wish;
import lombok.With;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface WishRepository  extends ReactiveMongoRepository<Wish, String> {

    Flux<Wish> findAllByUserId(String  userId);
}
