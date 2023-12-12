package com.akerke.wishlist.entity;

import com.akerke.wishlist.constants.Category;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document("wish")
@Getter
@Setter
public class Wish {

    @Id
    private String id;

    private String name;
    private String description;

    private Boolean isHappened;
    private Category category;

//    @JsonProperty("userId")
    public String userId;

//    @JsonGetter("userId")
//    String getUser(){
//        return this.user.getId();
//    }
}
