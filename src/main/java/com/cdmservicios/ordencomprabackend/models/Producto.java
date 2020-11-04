package com.cdmservicios.ordencomprabackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

@Data
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
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
    private Usuario usuario ;

    @OneToOne
    @JoinColumn(name = "idcategoria")
    private Categoria categoria ;


    public Integer getImagenHashCode(){
        return (this.imagen != null)? Arrays.hashCode(this.imagen) : null;
    }


    @PrePersist
    public void prePersis(){
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
