package Cibertec.Services;

import Cibertec.Model.Usuario;
import Cibertec.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepo;

    public UsuarioService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public Usuario registrar(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepo.findAll();
    }

    public Usuario obtener(Integer id) { // 👈 mejor que Long, porque el repo usa Integer
        return usuarioRepo.findById(id).orElse(null);
    }

    public Usuario login(String username, String clave) {
        Usuario u = usuarioRepo.findByUsername(username).orElse(null);
        if (u != null && u.getPassword().equals(clave)) {
            return u;
        }
        return null;
    }

}
