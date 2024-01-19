package com.example.demo.controller;

import com.example.demo.DTO.PostDTO;
import com.example.demo.entities.Post;
import com.example.demo.exceptions.PostNotFoundException;
import com.example.demo.exceptions.UsuarioNotFoundException;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<String> publicarPost(@RequestBody PostDTO postDTO) {
        try {
            postService.publicarPost(postDTO.getUsuarioId(), postDTO.getTitulo(), postDTO.getDescripcion());
            return new ResponseEntity<>("Post publicado correctamente", HttpStatus.CREATED);
        } catch (UsuarioNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{postId}")
    public ResponseEntity<String> editarPost(@PathVariable Long postId, @RequestBody String nuevoTitulo, @RequestBody String nuevaDescripcion) {
        try {
            postService.editarPost(postId, nuevoTitulo, nuevaDescripcion);
            return new ResponseEntity<>("Post editado correctamente", HttpStatus.OK);
        } catch (PostNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("usuario/{usuarioId}")
    public ResponseEntity<List<Post>> obtenerPostsDeUsuario(@PathVariable Long usuarioId) {
        try {
            List<Post> posts = postService.obtenerPostsDeUsuario(usuarioId);
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (UsuarioNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Post>> obtenerTodosLosPosts() {
        List<Post> posts = postService.obtenerTodosLosPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{postId}")
    public ResponseEntity<String> eliminarPost(@PathVariable Long postId) {
        postService.eliminarPost(postId);
        return new ResponseEntity<>("Post eliminado correctamente", HttpStatus.OK);
    }
}