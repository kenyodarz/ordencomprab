package com.cdmservicios.ordencomprabackend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
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
    @JoinColumn(name = "idordendecompra")
    public OrdenDeCompra ordenDeCompra;

    public Double total;

    @PrePersist
    public void prePersis() {
        this.fechaderegistro = new Date();
    }

    public Double getTotal() {
        this.total = this.cantidadsolicitada * this.precioinicial;
        return total;
    }
}
