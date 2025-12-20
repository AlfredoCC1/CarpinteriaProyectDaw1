package Cibertec.Productos.Controllers;

import Cibertec.Productos.Model.Producto;
import Cibertec.Productos.Services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producto/public")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoPublicController {

    private final ProductoService productoService;

    @GetMapping("/listar")
    public List<Producto> listarPublico() {
        return productoService.listar();
    }
}
