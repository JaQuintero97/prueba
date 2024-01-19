package com.example.demo.service;

import com.example.demo.entities.Post;
import com.example.demo.entities.Usuario;
import com.example.demo.exceptions.PostNotFoundException;
import com.example.demo.exceptions.UsuarioNotFoundException;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void publicarPost(Long usuarioId, String titulo, String descripcion)  throws UsuarioNotFoundException{
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);

        if (usuario.isPresent()) {
            Post nuevoPost = new Post();
            nuevoPost.setTitulo(titulo);
            nuevoPost.setDescripcion(descripcion);
            nuevoPost.setUsuario(usuario.get());

            postRepository.save(nuevoPost);
        } else {
            throw new UsuarioNotFoundException("Usuario no encontrado con ID: " + usuarioId);
        }
    }

    public void editarPost(Long postId, String nuevoTitulo, String nuevaDescripcion) throws PostNotFoundException {
        Optional<Post> postOptional = postRepository.findById(postId);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setTitulo(nuevoTitulo);
            post.setDescripcion(nuevaDescripcion);

            postRepository.save(post);
        } else {
            throw new PostNotFoundException("Post no encontrado con ID: " + postId);
        }
    }

    public List<Post> obtenerPostsDeUsuario(Long usuarioId) throws UsuarioNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);

        if (usuario.isPresent()) {
            return postRepository.findByUsuario(usuario.get());
        } else {
            throw new UsuarioNotFoundException("Usuario no encontrado con ID: " + usuarioId);
        }
    }

    public List<Post> obtenerTodosLosPosts() {
        return postRepository.findAll();
    }

    public void eliminarPost(Long postId) {
        postRepository.deleteById(postId);
    }
}