package Cibertec.Services;

import Cibertec.Model.Producto;
import Cibertec.Repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Registrar producto
    public Producto registrarProducto(Producto producto) {

        // Validar nombre único
        if (productoRepository.existsByNombre(producto.getNombre())) {
            throw new RuntimeException("Ya existe un producto con ese nombre.");
        }

        // Validar precio
        if (producto.getPrecio() == null || producto.getPrecio().doubleValue() <= 0) {
            throw new RuntimeException("El precio debe ser mayor a 0.");
        }

        // Validar stock
        if (producto.getStock() != null && producto.getStock() < 0) {
            throw new RuntimeException("El stock no puede ser negativo.");
        }

        return productoRepository.save(producto);
    }

    // Actualizar producto por ID
    public Producto actualizarProducto(Integer id, Producto nuevo) {

        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado."));

        // Validar duplicado si cambia el nombre
        if (!existente.getNombre().equals(nuevo.getNombre()) &&
                productoRepository.existsByNombre(nuevo.getNombre())) {
            throw new RuntimeException("Ya existe otro producto con ese nombre.");
        }

        // Validar precio
        if (nuevo.getPrecio() == null || nuevo.getPrecio().doubleValue() <= 0) {
            throw new RuntimeException("El precio debe ser mayor a 0.");
        }

        // Validar stock
        if (nuevo.getStock() != null && nuevo.getStock() < 0) {
            throw new RuntimeException("El stock no puede ser negativo.");
        }

        // Actualizar datos
        existente.setNombre(nuevo.getNombre());
        existente.setDescripcion(nuevo.getDescripcion());
        existente.setPrecio(nuevo.getPrecio());
        existente.setStock(nuevo.getStock());
        existente.setEstado(nuevo.getEstado());

        return productoRepository.save(existente);
    }

    // Obtener producto por ID
    public Producto obtenerProducto(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado."));
    }

    // Listar todos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // Eliminar producto
    public void eliminarProducto(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("El producto no existe.");
        }
        productoRepository.deleteById(id);
    }
}
