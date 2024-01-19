package com.example.demo.service;
import com.example.demo.entities.Usuario;
import com.example.demo.exceptions.UsuarioException;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService  {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> registrarUsuario(Usuario nuevoUsuario) throws UsuarioException {
        if (nuevoUsuario.getNombre() == null || nuevoUsuario.getNombre().isEmpty()) {
            throw new UsuarioException("El nombre del usuario no puede ser nulo o vacío");
        }

        if (nuevoUsuario.getApellido() == null || nuevoUsuario.getApellido().isEmpty()) {

            throw new UsuarioException("El apellido del usuario no puede ser nulo o vacío");
        }

        if (nuevoUsuario.getCorreo() == null || nuevoUsuario.getCorreo().isEmpty()) {
            throw new UsuarioException("El correo del usuario no puede ser nulo o vacío");
        }

        if (nuevoUsuario.getPassword() == null || nuevoUsuario.getPassword().isEmpty()) {
            throw new UsuarioException("El usuario debe tener una contraseña");
        }

        Usuario usuarioRepositoryByCorreo = usuarioRepository.findByCorreo(nuevoUsuario.getCorreo());

        if (usuarioRepositoryByCorreo != null) {
            throw new UsuarioException("El correo proporcionado ya está en uso");
        }
        return Optional.of(usuarioRepository.save(nuevoUsuario));
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long usuarioId) {
        return usuarioRepository.findById(usuarioId);
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByCorreo(username);
        if (usuarioBuscado.isPresent()) {
            return usuarioBuscado.get();
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }*/
}