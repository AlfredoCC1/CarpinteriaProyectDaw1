package Cibertec.Categoria.Repository;

import Cibertec.Categoria.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Traer solo categorías activas
    List<Categoria> findByActivoTrue();

    // Buscar por nombre (contiene, sin importar mayúsculas/minúsculas)
    List<Categoria> findByNombreContainingIgnoreCase(String nombre);
}
