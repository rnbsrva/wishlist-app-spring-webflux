package com.akerke.wishlist.dto;

import com.akerke.wishlist.constants.Category;

public record WishDTO(
        String userId,
        String name,
        String description,
        Boolean isHappened,
        Category category
){
}
