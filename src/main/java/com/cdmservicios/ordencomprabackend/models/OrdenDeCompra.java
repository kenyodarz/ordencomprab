package com.cdmservicios.ordencomprabackend.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "ordendecompra")
public class OrdenDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idordendecompra;

    @Column(insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numerodeorden;

    @Column
    private LocalDate fechadeorden;

    @OneToOne
    @JoinColumn(name = "idproveedor")
    private Proveedor proveedor;

    @Column
    private String condestinoa;

    @Column
    private String contacto;

    @Column
    private String concargoa;

    @Column
    private String transportador;

    @Column
    private String numerodeguia;

    @Column
    private String formadepago;

    @NotNull
    @OneToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    @Column
    private String observaciones;

    @OneToOne
    @JoinColumn(name = "idrequisicion")
    private Requisition requisition;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaderegistro;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechadeactualizado;

    @Column
    private Integer iva;

    @Column
    private Boolean exentodeiva;

    @Column
    private String centrodecostos;

    @Column
    private Double valoriva;

    @Column
    private Boolean anulada;

    @PrePersist
    public void prePersis() {
        this.fechaderegistro = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.fechadeactualizado = new Date();
    }

    public Integer getIdordendecompra() {
        return idordendecompra;
    }

    public Integer getIva() { return iva; }

    public Double getValoriva() { return valoriva; }
}
