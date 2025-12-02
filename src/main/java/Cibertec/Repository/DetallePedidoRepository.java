package Cibertec.Repository;

import Cibertec.Model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

    boolean existsByPedido_IdPedidoAndProducto_IdProducto(Integer idPedido, Integer idProducto);
   // Total de ventas por mes
    @Query("SELECT SUM(p.total) FROM Pedido p WHERE MONTH(p.fechaPedido) = :mes AND YEAR(p.fechaPedido) = :anio")
    BigDecimal totalVentasPorMes(@Param("mes") int mes, @Param("anio") int anio);

   //Total de ventas por año
   @Query("SELECT SUM(p.total) FROM Pedido p WHERE YEAR(p.fechaPedido) = :anio")
   BigDecimal totalVentasPorAnio(@Param("anio") int anio);

   //cantidad de productos vendido por mes
   @Query("SELECT SUM(d.cantidad) FROM DetallePedido d WHERE MONTH(d.pedido.fechaPedido) = :mes AND YEAR(d.pedido.fechaPedido) = :anio")
   Integer productosVendidosMes(@Param("mes") int mes, @Param("anio") int anio);

    //cantidad de servicios o pedidos realizados por mes
  @Query("SELECT COUNT(p) FROM Pedido p WHERE MONTH(p.fechaPedido) = :mes AND YEAR(p.fechaPedido) = :anio")
  Integer pedidosRealizadosMes(@Param("mes") int mes, @Param("anio") int anio);


   //productos mas vendidos
   @Query("SELECT d.producto.nombre, SUM(d.cantidad) as total "
           + "FROM DetallePedido d GROUP BY d.producto ORDER BY total DESC")
   List<Object[]> productosMasVendidos();


}


