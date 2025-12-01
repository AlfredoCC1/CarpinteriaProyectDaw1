package Cibertec.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import Cibertec.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);
}

