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
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "recepciondepedidos")
public class RecepcionDePedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idrecepciondepedido;
    @OneToOne
    @JoinColumn(name = "idpedido")
    private Pedido pedido;
    @Column
    private LocalDate fechaderecibido;
    @Column
    private Double cantidadrecibida;
    @Column
    private Double preciofinal;
    @Column
    private String factura;
    @Column
    private String remision;
    @Column
    private String observaciones;
    @OneToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date fechaderegistro;

    @PrePersist
    public void prePersist() {
        this.fechaderegistro = new Date();
    }


}
