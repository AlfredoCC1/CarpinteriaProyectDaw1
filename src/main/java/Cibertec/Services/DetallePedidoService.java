package Cibertec.Services;

import Cibertec.Model.DetallePedido;
import Cibertec.Model.Pedido;
import Cibertec.Repository.DetallePedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DetallePedidoService {

    private final DetallePedidoRepository detalleRepo;

    public DetallePedidoService(DetallePedidoRepository detalleRepo) {
        this.detalleRepo = detalleRepo;
    }

    // ==============================
    //  DASHBOARD - INDICADORES
    // ==============================

    // Total vendido en un mes
    public BigDecimal getVentasPorMes(int mes, int anio) {
        BigDecimal r = detalleRepo.totalVentasPorMes(mes, anio);
        return r != null ? r : BigDecimal.ZERO;
    }

    // Total vendido en un año
    public BigDecimal getVentasPorAnio(int anio) {
        return detalleRepo.totalVentasPorAnio(anio);
    }

    // Cantidad de productos vendidos por mes
    public Integer getProductosVendidosMes(int mes, int anio) {
        Integer r = detalleRepo.productosVendidosMes(mes, anio);
        return r != null ? r : 0;
    }

    // Cantidad de pedidos realizados en un mes
    public Integer getPedidosRealizadosMes(int mes, int anio) {
        Integer r = detalleRepo.pedidosRealizadosMes(mes, anio);
        return r != null ? r : 0;
    }

    // Productos más vendidos por mes
    public List<Object[]> getProductosMasVendidosMes(int mes, int anio) {
        return detalleRepo.productosMasVendidosMes(mes, anio);
    }


    // ==============================
    //  VALIDACIONES
    // ==============================

    // Verificar si un detalle ya existe en un pedido
    public boolean existeDetalle(Integer idPedido, Integer idProducto) {
        return detalleRepo.existsByPedido_IdPedidoAndProducto_IdProducto(idPedido, idProducto);
    }

    // Registrar un detalle de pedido con subtotal calculado
    public DetallePedido registrarDetalle(DetallePedido detalle) {

        // Calcular subtotal = precioUnitario * cantidad
        BigDecimal subtotal = detalle.getPrecioUnitario()
                .multiply(new BigDecimal(detalle.getCantidad()));

        detalle.setSubtotal(subtotal);

        return detalleRepo.save(detalle);
    }

    public void actualizarTotalPedido(Pedido pedido) {
        BigDecimal total = pedido.getDetalles().stream()
                .map(DetallePedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setTotal(total);
    }


}

