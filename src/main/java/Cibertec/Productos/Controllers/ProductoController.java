package Cibertec.Productos.Controllers;
/*
import Cibertec.Categoria.Model.Categoria;
import Cibertec.Productos.Model.Producto;
import Cibertec.Categoria.Services.CategoriaService;
import Cibertec.Productos.Services.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
/*
@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoController(ProductoService productoService,
                              CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    // =========================
    // LISTAR (si lo necesitas)
    // =========================
    @GetMapping
    public String listarProductos(@RequestParam(name = "categoria", required = false) Long idCategoria,
                                  Model model) {

        List<Categoria> categorias = categoriaService.listarActivas();
        List<Producto> productos;

        if (idCategoria != null) {
            Categoria cat = categoriaService.buscarPorId(idCategoria);
            if (cat == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada");
            }
            productos = productoService.listarPorCategoriaActivos(cat);
            model.addAttribute("categoriaSeleccionada", idCategoria);
        } else {
            productos = productoService.listarActivos();
        }

        model.addAttribute("categorias", categorias);
        model.addAttribute("productos", productos);
        return "productos"; // otra vista, si la usas
    }

    // =========================
    // GUARDAR PRODUCTO
    // =========================
    @PostMapping("/guardar")
    public String guardarProducto(
            @RequestParam String nombre,
            @RequestParam(required = false) BigDecimal precio,
            @RequestParam Long idCategoria,
            @RequestParam(name = "descCorta", required = false) String descCorta,
            @RequestParam(name = "descLarga", required = false) String descLarga,
            @RequestParam(required = false) String carac1,
            @RequestParam(required = false) String carac2,
            @RequestParam(required = false) String carac3,
            @RequestParam(required = false) String carac4,
            @RequestParam(required = false) Integer largo,
            @RequestParam(required = false) Integer ancho,
            @RequestParam(required = false) Integer altura,
            @RequestParam(required = false) BigDecimal peso
    ) {

        // 1) Buscar categoría
        Categoria categoria = categoriaService.buscarPorId(idCategoria);
        if (categoria == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Categoría no válida");
        }

        // 2) Crear y llenar Producto (tabla producto)
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);              // puede ser null → etiqueta "A cotizar"
        producto.setDescripcionCorta(descCorta); // asegúrate que exista en la entidad
        producto.setCategoria(categoria);
        producto.setActivo(true);                // Boolean o boolean → TINYINT(1)

        if (precio == null) {
            producto.setEtiquetaPrecio("A cotizar");
        }

        // Guarda en la tabla PRODUCTO
        productoService.guardar(producto);

        // 3) Aquí más adelante puedes guardar:
        //    - ProductoDetalle (descLarga, medidas)
        //    - Caracteristicas (carac1..carac4)
        //    - Imágenes (img_card_url, etc.)
        // usando producto.getIdProducto()

        // 4) Redirigir al dashboard
        return "redirect:/dashboard";
    }
}*/
