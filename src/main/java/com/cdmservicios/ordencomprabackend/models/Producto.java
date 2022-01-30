package com.cdmservicios.ordencomprabackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Arrays;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idproducto;

    @Column
    private String codigoproducto;

    @Column
    private String nombreproducto;

    @Column
    private String medida;

    @JsonIgnore
    private byte[] imagen;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaderegistro;

    @OneToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "idcategoria")
    private Categoria categoria;


    public Integer getImagenHashCode() {
        return (this.imagen != null) ? Arrays.hashCode(this.imagen) : null;
    }


    @PrePersist
    public void prePersis() {
        this.fechaderegistro = new Date();
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
