package Cibertec.Login.Model;

import Cibertec.Model.Empleado;
import Cibertec.Model.EstadoUsuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    // FK a empleado (1:1)
    @OneToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    @ToString.Exclude   // ← evita el ciclo infinito
    private Empleado empleado;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 10)
    private EstadoUsuario estado = EstadoUsuario.ACTIVO;

    @PrePersist
    public void prePersist() {
        if (estado == null) {
            estado = EstadoUsuario.ACTIVO;
      }
    }
}

