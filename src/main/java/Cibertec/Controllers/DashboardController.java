package Cibertec.Controllers;

import Cibertec.Services.ClienteService;
import Cibertec.Services.ProductoService;
import Cibertec.Services.DetallePedidoService;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final ClienteService clienteService;
    private final ProductoService productoService;
    private final DetallePedidoService detallePedidoService;

    public DashboardController(ClienteService clienteService,
                               ProductoService productoService,
                               DetallePedidoService detallePedidoService) {
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.detallePedidoService = detallePedidoService;
    }

    // ==========================
    //   VISTA PRINCIPAL
    // ==========================
    @GetMapping("")
    public String dashboard(HttpSession session, Model model) {

        Object usuarioLogueado = session.getAttribute("usuarioLogueado");
        if (usuarioLogueado == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuarioLogueado);

        return "dashboard"; // templates/dashboard.html
    }


    // ==========================
    //   ENDPOINTS REST (JSON)
    // ==========================

    @GetMapping("/ventas-mes")
    @ResponseBody
    public BigDecimal ventasPorMes(@RequestParam int mes, @RequestParam int anio) {
        return detallePedidoService.getVentasPorMes(mes, anio);
    }

    @GetMapping("/ventas-anio")
    @ResponseBody
    public BigDecimal ventasPorAnio(@RequestParam int anio) {
        return detallePedidoService.getVentasPorAnio(anio);
    }

    @GetMapping("/productos-vendidos")
    @ResponseBody
    public Integer productosVendidosMes(@RequestParam int mes, @RequestParam int anio) {
        return detallePedidoService.getProductosVendidosMes(mes, anio);
    }

    @GetMapping("/pedidos-mes")
    @ResponseBody
    public Integer pedidosMes(@RequestParam int mes, @RequestParam int anio) {
        return detallePedidoService.getPedidosRealizadosMes(mes, anio);
    }

    @GetMapping("/productos-mas-vendidos")
    @ResponseBody
    public List<Object[]> productosMasVendidos() {
        return detallePedidoService.getProductosMasVendidos();
    }
}

