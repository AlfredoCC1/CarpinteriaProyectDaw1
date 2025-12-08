package Cibertec.Productos.Services;

import Cibertec.Categoria.Model.Categoria;
import Cibertec.Productos.Model.Producto;
import Cibertec.Productos.Repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public List<Producto> listarActivos() {
        return productoRepository.findByActivoTrue();
    }

    public List<Producto> listarPorCategoriaActivos(Categoria categoria) {
        return productoRepository.findByCategoriaAndActivoTrue(categoria);
    }

    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminarFisico(Long id) {
        productoRepository.deleteById(id);
    }

    // "Eliminar" lógico → poner activo = false
    public void desactivar(Long id) {
        productoRepository.findById(id).ifPresent(p -> {
            p.setActivo(false);
            productoRepository.save(p);
        });
    }
}
