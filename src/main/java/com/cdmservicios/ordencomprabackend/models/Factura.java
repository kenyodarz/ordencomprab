package com.cdmservicios.ordencomprabackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Date;

@Data
@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idfactura;

    @JsonIgnore
    private byte[] archivo;

    @Column
    private String formato;

    @Column
    private String nombrearchivo;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date fechaderegistro;

    @NotNull
    @OneToOne
    @JoinColumn(name = "idordendecompra")
    public OrdenDeCompra ordenDeCompra;

    @OneToOne
    @JoinColumn(name = "idrecepciondepedido")
    public RecepcionDePedidos recepcionDePedidos;


    public Integer getArchivoHashCode(){
        return (this.archivo != null)? Arrays.hashCode(this.archivo) : null;
    }

    @PrePersist
    public void prePersist(){
        this.fechaderegistro = new Date();
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public void setOrdenDeCompra(OrdenDeCompra ordenDeCompra) {
        this.ordenDeCompra = ordenDeCompra;
    }
}
