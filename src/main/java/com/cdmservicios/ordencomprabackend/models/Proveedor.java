package com.cdmservicios.ordencomprabackend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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

    public void addProducto(Producto producto) {
        this.productos.add(producto);
    }

    public void removeProducto(Producto producto) {
        this.productos.remove(producto);
    }

    @PrePersist
    public void prePersis() {
        this.fechaderegistro = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaactualizado = new Date();
    }
}
