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
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cotizaciones")
public class Cotizaciones {

    @Column
    public String formato;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcotizacion;
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

    public Integer getArchivoHashCode() {
        return (this.archivo != null) ? Arrays.hashCode(this.archivo) : null;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }
}
