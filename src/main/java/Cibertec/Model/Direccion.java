package Cibertec.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Direccion")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Integer idDireccion;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;

    @Column(name = "ciudad", length = 100)
    private String ciudad;

    @Column(name = "provincia", length = 100)
    private String provincia;

    @Column(name = "codigo_postal", length = 20)
    private String codigoPostal;

    @Column(name = "pais", length = 100)
    private String pais;
}

