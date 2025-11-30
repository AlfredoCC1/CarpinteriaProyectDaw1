package Cibertec.Repository;

import Cibertec.Model.EstadoProducto;
import Cibertec.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Ver si un nombre ya existe (evita duplicados)
    boolean existsByNombre(String nombre);

}
