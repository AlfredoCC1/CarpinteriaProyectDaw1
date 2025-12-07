package Cibertec.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VistasController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/registra-cuenta")
    public String mostrarFormularioRegistro() {
        return "register";
    }

    @GetMapping("/productos")
    public String producto() {
        return "productos";
    }

    @GetMapping("/proceso")
    public String proceso() {
        return "proceso";
    }

    @GetMapping("/servicios")
    public String servicio() {
        return "servicios";
    }

    @GetMapping("/cotizacion")
    public String cotizacion() {
        return "cotizacion";
    }
}
