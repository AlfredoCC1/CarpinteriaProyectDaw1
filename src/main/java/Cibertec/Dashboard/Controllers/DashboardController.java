package Cibertec.Dashboard.Controllers;

import Cibertec.Productos.Services.ProductoService;
import Cibertec.Categoria.Services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public DashboardController(ProductoService productoService,
                               CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        // 👉 categorías para el <select>
        model.addAttribute("categorias", categoriaService.listarActivas());
        return "dashboard";  // dashboard.html en /templates
    }
}
