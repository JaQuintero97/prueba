package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String titulo;
    @Column
    private String descripcion;
   /* @Lob
    private byte[] imagen;
*/
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Post() {
    }
}