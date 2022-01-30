package com.cdmservicios.ordencomprabackend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
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

    public Integer getIva() {
        return iva;
    }

    public Double getValoriva() {
        return valoriva;
    }
}
