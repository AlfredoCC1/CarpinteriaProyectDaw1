package Cibertec.Productos.Model;

import Cibertec.Categoria.Model.Categoria;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    // FK a categoria.id_categoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    @Column(name = "descripcion_corta", length = 255)
    private String descripcionCorta;

    @Column(name = "precio", precision = 10, scale = 2)
    private BigDecimal precio;      // puede ser null -> "A cotizar"

    @Column(name = "etiqueta_precio", length = 40)
    private String etiquetaPrecio;  // ej: "A cotizar"

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;  // mapea el TINYINT(1)

    @Column(name = "img_card_url", length = 255)
    private String imgCardUrl;      // imagen tarjeta productos.html

    @Column(name = "img_hero_url", length = 255)
    private String imgHeroUrl;      // imagen hero detalle.html

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn;

    @PrePersist
    public void prePersist() {
        this.creadoEn = LocalDateTime.now();
        this.actualizadoEn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.actualizadoEn = LocalDateTime.now();
    }
}
