package Cibertec.Productos.Repository;

import Cibertec.Categoria.Model.Categoria;
import Cibertec.Productos.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // para productos.html (solo activos)
    List<Producto> findByActivoTrue();

    // para filtrar por categoría
    List<Producto> findByCategoriaAndActivoTrue(Categoria categoria);
}
