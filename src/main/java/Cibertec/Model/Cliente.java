package Cibertec.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "dni", nullable = false, unique = true, length = 15)
    private String dni;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "correo", unique = true, length = 100)
    private String correo;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    // Relación 1:N con Direccion
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Direccion> direcciones;

    // Relación 1:N con Pedido
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;
}
