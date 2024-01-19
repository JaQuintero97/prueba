package com.example.demo.repository;

import com.example.demo.DTO.PostDTO;
import com.example.demo.entities.Post;
import com.example.demo.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUsuario(Usuario usuario);
}
