package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 25, nullable = false )
    private String nombre;
    @Column(length = 25, nullable = false )
    private String apellido;
    @Column
    private String correo;
    @Column(length = 25, nullable = false )
    private String password;


    public Usuario() {
    }
}


