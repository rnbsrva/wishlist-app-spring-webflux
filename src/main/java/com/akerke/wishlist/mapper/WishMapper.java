package com.akerke.wishlist.mapper;

import com.akerke.wishlist.dto.WishDTO;
import com.akerke.wishlist.entity.User;
import com.akerke.wishlist.entity.Wish;
import org.mapstruct.*;

@Mapper(
        imports = {User.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface WishMapper {

    @Mapping(target = "id", ignore = true)
    Wish toModel(WishDTO wishDTO);

    WishDTO toDTO(Wish wish);

    @Mapping(target = "id", ignore = true)
    void update(WishDTO wishDTO, @MappingTarget Wish wish);

}
