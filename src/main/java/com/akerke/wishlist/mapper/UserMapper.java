package com.akerke.wishlist.mapper;

import com.akerke.wishlist.dto.UserDTO;
import com.akerke.wishlist.entity.User;
import com.akerke.wishlist.entity.Wish;
import org.mapstruct.*;

import java.util.ArrayList;

@Mapper(
        imports = {Wish.class, ArrayList.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface UserMapper {

    @Mapping(target = "wishList", expression = "java(new ArrayList<Wish>())")
    User toModel(UserDTO userDTO);

    UserDTO toDTO(User user);

    @Mapping(target = "id", ignore = true)
    void update(UserDTO userDTO, @MappingTarget User user);

}
