package Cibertec.Productos.Model;

import Cibertec.Categoria.Model.Categoria;
import Cibertec.LineaDiseno.Model.LineaDiseno;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name="producto")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name="id_producto")
    private Long idProducto;



    // ========================= RELACION CATEGORIA ======================
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;


    // ========================= RELACION LINEA DISEÑO ===================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_linea", nullable = true)
    private LineaDiseno lineaDiseno;


    // ========================= CAMPOS BÁSICOS ==========================
    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(name="descripcion_card", length = 200)
    private String descripcionCard;

    @Column(name="descripcion_corta", length = 350)
    private String descripcionCorta;

    @Column(name="descripcion_larga", columnDefinition = "TEXT")
    private String descripcionLarga;

    // ========================= PRECIO ==========================
    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name="etiqueta_precio", length = 40)
    private String etiquetaPrecio = "A cotizar";

    // ========================= DIMENSIONES ==========================
    private Integer largo;
    private Integer ancho;
    private Integer altura;
    private Integer peso;

    private String material;

    // ========================= IMAGENES ==========================
    @Column(length = 255)
    private String imagen1;
    @Column(length = 255)
    private String imagen2;
    @Column(length = 255)
    private String imagen3;

    private Boolean destacado = false;

    @Column(name = "activo")
    private Boolean activo = true;


    // ========================= FECHAS ==========================
    @Column(name="fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name="fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @PrePersist
    public void prePersist(){
        fechaCreacion = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        fechaModificacion = LocalDateTime.now();
    }


}
