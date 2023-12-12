package com.akerke.wishlist.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document("users")
@Getter
@Setter
public class User {

    @Id
    private String id;

    private String name;
    private String surname;

    private String email;
    private String password;

    @DocumentReference(collection = "wish")
    private List<Wish> wishList;
}
