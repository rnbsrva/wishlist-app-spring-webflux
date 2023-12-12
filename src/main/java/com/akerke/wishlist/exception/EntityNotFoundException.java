package com.akerke.wishlist.exception;


public class EntityNotFoundException extends RuntimeException{


    public EntityNotFoundException(Class<?> entityClass, Object entityId) {
        super("%s with id: %s not found".formatted(entityClass.getSimpleName(), entityId.toString()));
    }

    public EntityNotFoundException(String errorMessage){
        super(errorMessage);
    }

}