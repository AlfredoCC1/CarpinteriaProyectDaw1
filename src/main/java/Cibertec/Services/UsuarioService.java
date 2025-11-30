package Cibertec.Services;

import Cibertec.Model.Usuario;
import Cibertec.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepo;

    public UsuarioService(UsuarioRepository usuarioRepo) {

        this.usuarioRepo = usuarioRepo;
    }

    // Registrar usuario (validación de username único)
    public Usuario registrar(Usuario usuario) {

        if (usuarioRepo.existsByUsername(usuario.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está registrado.");
        }

        usuario.setFechaCreacion(LocalDateTime.now());
        return usuarioRepo.save(usuario);
    }

    // Listar todos los usuarios
    public List<Usuario> listar() {
        return usuarioRepo.findAll();
    }

    // Obtener usuario por ID
    public Usuario obtener(Integer id) {
        return usuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    // Login de usuario
    public Usuario login(String username, String clave) {

        Usuario usuario = usuarioRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getPassword().equals(clave)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuario;
    }

}
