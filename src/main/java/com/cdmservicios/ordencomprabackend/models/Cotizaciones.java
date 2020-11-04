package com.cdmservicios.ordencomprabackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@Data
@Entity
@Table(name = "cotizaciones")
public class Cotizaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcotizacion;

    @Column
    public String formato;

    @Column
    private String nombrearchivo;

    @Column
    private LocalDateTime fechaderegistro;

    @OneToOne
    @JoinColumn(name = "idrequisicion")
    private Requisition requisition;

    //@Lob()
    @JsonIgnore
    private byte[] archivo;

    public Integer getArchivoHashCode(){
        return (this.archivo != null)? Arrays.hashCode(this.archivo) : null;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }
}
