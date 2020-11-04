package com.cdmservicios.ordencomprabackend.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idproveedor;

    @Column
    private String nombreprovee;
    @Column
    private String nitprovee;
    @Column
    private String direccionprovee;
    @Column
    private String telefonofijoprovee;
    @Column
    private String celularprovee;
    @Column
    private String correoprovee;
    @Column
    private String paginawebprovee;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaderegistro;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaactualizado;
    @OneToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;
    @Column
    private String ciudad;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "producto_proveedor",
            joinColumns = @JoinColumn(name = "idproveedor"),
            inverseJoinColumns = @JoinColumn(name = "idproducto"))
    private List<Producto> productos;

    public void addProducto(Producto producto){
        this.productos.add(producto);
    }

    public void removeProducto(Producto producto){
        this.productos.remove(producto);
    }

    @PrePersist
    public void prePersis(){
        this.fechaderegistro = new Date();
    }

    @PreUpdate
    public void preUpdate(){
        this.fechaactualizado = new Date();
    }
}
