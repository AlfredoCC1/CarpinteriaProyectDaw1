package Cibertec.Login.Controller;

import Cibertec.Login.Model.Usuario;
import Cibertec.Login.Service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Mostrar formulario de login
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";   // templates/login.html
    }

    // Procesar formulario de login (solo JAVA)
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        try {
            Usuario usuario = usuarioService.login(username, password);

            // Guardamos al usuario en sesión
            session.setAttribute("usuarioLogueado", usuario);

            // Redirigimos al dashboard
            return "redirect:/dashboard";

        } catch (RuntimeException e) {
            // Si falla (credenciales, inactivo, etc.) volvemos al login con mensaje
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    // Página del dashboard (vista protegida manualmente)
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        // Chequeo simple de sesión (opcional, pero recomendable)
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        return "dashboard";   // templates/dashboard.html
    }

    // Cerrar sesión
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
