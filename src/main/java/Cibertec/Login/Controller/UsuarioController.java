package Cibertec.Login.Controller;

import Cibertec.Login.Enun.EstadoUsuario;
import Cibertec.Login.Model.Usuario;
import Cibertec.Login.Service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Registrar usuario para un empleado
    @PostMapping("/empleado/{idEmpleado}")
    public ResponseEntity<?> registrarParaEmpleado(@PathVariable Integer idEmpleado,
                                                   @RequestBody Usuario datosUsuario) {
        try {
            Usuario guardado = usuarioService.registrarUsuarioParaEmpleado(idEmpleado, datosUsuario);
            return ResponseEntity.ok(guardado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Listar todos los usuarios
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarUsuarios();
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        try {
            Usuario usuario = usuarioService.obtenerPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Cambiar estado (ACTIVO / INACTIVO)
    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Integer id,
                                           @RequestParam String estado) {
        try {
            EstadoUsuario nuevoEstado = EstadoUsuario.valueOf(estado.toUpperCase());
            Usuario actualizado = usuarioService.cambiarEstado(id, nuevoEstado);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Estado inválido. Use ACTIVO o INACTIVO.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
