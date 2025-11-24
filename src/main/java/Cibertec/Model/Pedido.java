package Cibertec.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "fecha_pedido")
    private LocalDateTime fechaPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoPedido estado;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    // Relación 1:N con Detalle_Pedido
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles;
}
