package com.cdmservicios.ordencomprabackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "facturas")
public class Factura {

    @NotNull
    @OneToOne
    @JoinColumn(name = "idordendecompra")
    public OrdenDeCompra ordenDeCompra;
    @OneToOne
    @JoinColumn(name = "idrecepciondepedido")
    public RecepcionDePedidos recepcionDePedidos;
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

    public Integer getArchivoHashCode() {
        return (this.archivo != null) ? Arrays.hashCode(this.archivo) : null;
    }

    @PrePersist
    public void prePersist() {
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
