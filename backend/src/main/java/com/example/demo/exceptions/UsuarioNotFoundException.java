package com.example.demo.exceptions;

public class UsuarioNotFoundException extends Exception{
    public UsuarioNotFoundException(String mensaje){
        super(mensaje);
    }
}
