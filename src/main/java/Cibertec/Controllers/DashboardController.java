package Cibertec.Controllers;

import Cibertec.Services.ClienteService;
import Cibertec.Services.ProductoService;
//import Cibertec.Services.PedidoService; // te doy una idea abajo
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final ClienteService clienteService;
    private final ProductoService productoService;
   // private final PedidoService pedidoService;

    public DashboardController(ClienteService clienteService,
                               ProductoService productoService){//,
          //                     PedidoService pedidoService) {
        this.clienteService = clienteService;
        this.productoService = productoService;
     //   this.pedidoService = pedidoService;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        // 1. Validar que haya usuario logueado
        Object usuarioLogueado = session.getAttribute("usuarioLogueado");
        if (usuarioLogueado == null) {
            return "redirect:/login";
        }

       /* // 2. Datos para las tarjetas
        long totalClientes = clienteService.listarClientes().size();
        long totalProductos = productoService.listarProductos().size();
        //long pedidosMes = pedidoService.contarPedidosDelMesActual();

        model.addAttribute("totalClientes", totalClientes);
        model.addAttribute("totalProductos", totalProductos);
       // model.addAttribute("pedidosMes", pedidosMes);

        // 3. Datos para gráficos
       // List<String> ventasLabels = pedidoService.obtenerLabelsVentasMensuales();
       // List<Double> ventasData = pedidoService.obtenerMontosVentasMensuales();

      //  List<String> prodLabels = pedidoService.obtenerNombresProductosMasVendidos();
      //  List<Integer> prodData = pedidoService.obtenerCantidadesProductosMasVendidos();

      //  model.addAttribute("ventasLabels", ventasLabels);
      //  model.addAttribute("ventasData", ventasData);
      //  model.addAttribute("prodLabels", prodLabels);
     //   model.addAttribute("prodData", prodData);*/

        // 4. Usuario para mostrar en la vista (barra superior)
        model.addAttribute("usuario", usuarioLogueado);

        return "dashboard"; // templates/dashboard.html
    }
}
