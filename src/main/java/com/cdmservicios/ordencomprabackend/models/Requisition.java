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
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "idcentrodecostos")
    private CentroDeCostos centroDeCostos;

    @Column
    private String observaciones;

    @PrePersist
    public void prePersist() {
        this.fechaderegistro = new Date();
    }

    public Integer getIdrequisicion() {
        return idrequisicion;
    }

    public void setIdrequisicion(Integer idrequisicion) {
        this.idrequisicion = idrequisicion;
    }
}
