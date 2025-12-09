package Cibertec.Categoria.Controller;

import Cibertec.Categoria.Model.Categoria;
import Cibertec.Categoria.Service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/api/listar")
    @ResponseBody
    public List<Categoria> apiListar(){
        return categoriaService.listar();
    }

    @PostMapping("/api/registrar")
    @ResponseBody
    public Categoria apiRegistrar(@RequestBody Categoria categoria){
        return categoriaService.registrar(categoria);
    }

    @DeleteMapping("/api/eliminar/{id}")
    @ResponseBody
    public void apiEliminar(@PathVariable Long id){
        categoriaService.eliminar(id);
    }
    @GetMapping("/api/buscar")
    @ResponseBody
    public List<Categoria> apiBuscar(@RequestParam String texto){
        return categoriaService.buscarPorNombre(texto);
    }

    @PutMapping("/api/editar/{id}")
    @ResponseBody
    public Categoria apiEditar(@PathVariable Long id, @RequestBody Categoria categoria){

        Categoria catDB = categoriaService.buscarPorId(id).orElse(null);

        if(catDB == null){
            throw new RuntimeException("No existe la categoría con id "+id);
        }

        catDB.setNombre(categoria.getNombre());
        catDB.setDescripcion(categoria.getDescripcion());
        catDB.setActivo(categoria.getActivo());

        return categoriaService.registrar(catDB); // <- actualiza
    }





}
