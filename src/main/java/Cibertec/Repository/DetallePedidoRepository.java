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
   @Query("""
SELECT SUM(d.subtotal)
FROM DetallePedido d
WHERE MONTH(d.pedido.fecha) = :mes 
  AND YEAR(d.pedido.fecha) = :anio
""")
   BigDecimal totalVentasPorMes(@Param("mes") int mes, @Param("anio") int anio);


    @Query("""
SELECT SUM(d.subtotal)
FROM DetallePedido d
WHERE YEAR(d.pedido.fecha) = :anio
""")
    BigDecimal totalVentasPorAnio(@Param("anio") int anio);


    @Query("SELECT SUM(d.cantidad) FROM DetallePedido d WHERE MONTH(d.pedido.fecha) = :mes AND YEAR(d.pedido.fecha) = :anio")
    Integer productosVendidosMes(@Param("mes") int mes, @Param("anio") int anio);

    @Query("SELECT COUNT(p) FROM Pedido p WHERE MONTH(p.fecha) = :mes AND YEAR(p.fecha) = :anio")
    Integer pedidosRealizadosMes(@Param("mes") int mes, @Param("anio") int anio);


   //productos mas vendidos
   @Query("""
    SELECT d.producto.nombre, SUM(d.cantidad)
    FROM DetallePedido d
    WHERE MONTH(d.pedido.fecha) = :mes
      AND YEAR(d.pedido.fecha) = :anio
    GROUP BY d.producto.nombre
    ORDER BY SUM(d.cantidad) DESC
""")
   List<Object[]> productosMasVendidosMes(
           @Param("mes") int mes,
           @Param("anio") int anio
   );



}


