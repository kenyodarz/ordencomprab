package com.cdmservicios.ordencomprabackend.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "requisicion")
public class Requisition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idrequisicion;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false)
    private Integer numerorequisicion;

    @Column
    private String referencia;

    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaderegistro;

    @OneToOne
    @JoinColumn (name = "idusuario")
    private Usuario usuario;

    @OneToOne
    @JoinColumn (name = "idcentrodecostos")
    private CentroDeCostos centroDeCostos;

    @Column
    private String observaciones;

    @PrePersist
    public void prePersist(){
        this.fechaderegistro = new Date();
    }

    public Integer getIdrequisicion() {
        return idrequisicion;
    }

    public void setIdrequisicion(Integer idrequisicion) {
        this.idrequisicion = idrequisicion;
    }
}
