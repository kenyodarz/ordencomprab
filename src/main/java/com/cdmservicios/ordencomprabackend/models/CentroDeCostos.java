package com.cdmservicios.ordencomprabackend.models;

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

@Getter
@Setter
@NoArgsConstructor
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
    @JoinColumn(name = "idusuario")
    private Usuario usuario;
    @OneToOne
    @JoinColumn(name = "idcliente")
    private Cliente clienteT;

}
