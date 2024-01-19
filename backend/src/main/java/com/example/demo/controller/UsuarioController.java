package com.example.demo.controller;

import com.example.demo.entities.Usuario;
import com.example.demo.exceptions.UsuarioException;
import com.example.demo.service.UsuarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Buscar Usuario (Para poder ver su perfil)
    @GetMapping("{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id){
        ResponseEntity response;
        try {
            response = ResponseEntity.status(200).body(usuarioService.obtenerUsuarioPorId(id));
        }catch (Exception e){
            response = ResponseEntity.badRequest().body(e.getMessage());
        }
        return response;
    }



    //Crear Usuario
    @PostMapping
    public ResponseEntity<Usuario> agregarUsuario(@RequestBody Usuario usuario) throws UsuarioException {
        ResponseEntity response;

        try {
            response = ResponseEntity.status(201).body(usuarioService.registrarUsuario(usuario));
        } catch (UsuarioException e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
        }
        return response;
    }


}
