package com.cdmservicios.ordencomprabackend.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
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
