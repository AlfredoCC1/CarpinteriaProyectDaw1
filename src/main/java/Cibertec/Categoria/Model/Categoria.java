package Cibertec.Categoria.Model;

import Cibertec.Productos.Model.Producto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;

    // TINYINT(1) -> Boolean
    @Column(name = "activo")
    private Boolean activo;

    // Relación inversa: una categoría tiene varios productos
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;
}
