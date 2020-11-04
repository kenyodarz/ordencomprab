package com.cdmservicios.ordencomprabackend.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcategoria;
    @Column
    private String nombrecategoria;
    @Column
    private String descripcioncategoria;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaderegistro;
    @Column
    private Long idusuario;

    public Categoria() {
    }

    public Long getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Long idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNombrecategoria() {
        return nombrecategoria;
    }

    public void setNombrecategoria(String nombrecategoria) {
        this.nombrecategoria = nombrecategoria;
    }

    public String getDescripcioncategoria() {
        return descripcioncategoria;
    }

    public void setDescripcioncategoria(String descripcioncategoria) {
        this.descripcioncategoria = descripcioncategoria;
    }

    public Date getFecharegistro() {
        return fechaderegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fechaderegistro = fecharegistro;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }
}
