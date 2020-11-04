package com.cdmservicios.ordencomprabackend.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "centrodecostos")
public class CentroDeCostos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcentrodecostos;
    @Column
    private String centrodecostos;
    @Column
    private String cliente;
    @OneToOne
    @JoinColumn (name = "idusuario")
    private Usuario usuario;
    @OneToOne
    @JoinColumn (name = "idcliente")
    private Cliente clienteT;

}
