package Cibertec.Categoria.Controller;

import Cibertec.Categoria.Model.Categoria;
import Cibertec.Categoria.Service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("categorias", categoriaService.listar());
        return "categoria/listar";
    }

    @GetMapping("/crear")
    public String crear(Model model){
        model.addAttribute("categoria", new Categoria());
        return "categoria/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Categoria categoria){
        categoriaService.registrar(categoria);
        return "redirect:/categoria/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model){
        model.addAttribute("categoria", categoriaService.buscarPorId(id).orElse(null));
        return "categoria/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        categoriaService.eliminar(id);
        return "redirect:/categoria/listar";
    }
}
