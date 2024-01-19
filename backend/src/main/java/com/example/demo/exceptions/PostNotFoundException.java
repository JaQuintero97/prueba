package com.example.demo.exceptions;

public class PostNotFoundException extends Exception {
    public PostNotFoundException(String mensaje){
        super(mensaje);
    }
}
