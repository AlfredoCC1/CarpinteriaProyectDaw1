package Cibertec.Services;

import Cibertec.Model.Empleado;
import Cibertec.Model.EstadoUsuario;
import Cibertec.Model.Usuario;
import Cibertec.Repository.EmpleadoRepository;
import Cibertec.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmpleadoRepository empleadoRepository;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          EmpleadoRepository empleadoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.empleadoRepository = empleadoRepository;
    }

    // Registrar usuario para un empleado
    public Usuario registrarUsuarioParaEmpleado(Integer idEmpleado, Usuario datosUsuario) {

        Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con id: " + idEmpleado));

        // Validar username único
        if (usuarioRepository.existsByUsername(datosUsuario.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está en uso: " + datosUsuario.getUsername());
        }

        Usuario usuario = new Usuario();
        usuario.setEmpleado(empleado);
        usuario.setUsername(datosUsuario.getUsername());
        usuario.setPassword(datosUsuario.getPassword()); // ⚠ aquí luego puedes encriptar

        // Estado por defecto ACTIVO
        usuario.setEstado(EstadoUsuario.ACTIVO);

        return usuarioRepository.save(usuario);
    }

    // Listar todos los usuarios
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Obtener usuario por ID
    public Usuario obtenerPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    // Cambiar estado de usuario (ACTIVO / INACTIVO)
    public Usuario cambiarEstado(Integer id, EstadoUsuario nuevoEstado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        usuario.setEstado(nuevoEstado);
        return usuarioRepository.save(usuario);
    }

    // (Opcional) Login súper básico (solo ejemplo)
    public Usuario login(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario o contraseña incorrectos."));

        if (!usuario.getPassword().equals(password)) {
            throw new RuntimeException("Usuario o contraseña incorrectos.");
        }

        if (usuario.getEstado() != EstadoUsuario.ACTIVO) {
            throw new RuntimeException("El usuario está inactivo.");
        }

        return usuario;
    }
}
