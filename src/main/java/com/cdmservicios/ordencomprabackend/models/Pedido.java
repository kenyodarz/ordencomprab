package com.cdmservicios.ordencomprabackend.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idpedido;
    @OneToOne
    @JoinColumn(name = "idrequisicion")
    public Requisition requisition;
    @OneToOne
    @JoinColumn(name = "idproducto")
    public Producto producto;
    @Column
    public Double cantidadsolicitada;
    @Column
    public Double precioinicial;
    @Column
    public String destino;
    @Column
    public String observaciones;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    public Date fechaderegistro;
    @OneToOne
    @JoinColumn(name="idordendecompra")
    public OrdenDeCompra ordenDeCompra;

    public Double total;

    @PrePersist
    public void prePersis(){
        this.fechaderegistro = new Date();
    }

    public Double getTotal() {
        this.total = this.cantidadsolicitada * this.precioinicial;
        return total;
    }
}
