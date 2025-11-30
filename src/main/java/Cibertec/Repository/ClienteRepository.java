package Cibertec.Repository;

import Cibertec.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // Buscar un cliente por DNI
    Optional<Cliente> findByDni(String dni);

    // Validar si un DNI ya existe (para evitar repetidos)
    boolean existsByDni(String dni);
}
