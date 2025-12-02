package Cibertec.Services;

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
        return detalleRepo.totalVentasPorMes(mes, anio);
    }

    // Total vendido en un año
    public BigDecimal getVentasPorAnio(int anio) {
        return detalleRepo.totalVentasPorAnio(anio);
    }

    // Cantidad de productos vendidos por mes
    public Integer getProductosVendidosMes(int mes, int anio) {
        return detalleRepo.productosVendidosMes(mes, anio);
    }

    // Cantidad de pedidos realizados en un mes
    public Integer getPedidosRealizadosMes(int mes, int anio) {
        return detalleRepo.pedidosRealizadosMes(mes, anio);
    }

    // Productos más vendidos
    public List<Object[]> getProductosMasVendidos() {
        return detalleRepo.productosMasVendidos();
    }

    // ==============================
    //  VALIDACIONES
    // ==============================

    // Verificar si un detalle ya existe en un pedido
    public boolean existeDetalle(Integer idPedido, Integer idProducto) {
        return detalleRepo.existsByPedido_IdPedidoAndProducto_IdProducto(idPedido, idProducto);
    }
}

