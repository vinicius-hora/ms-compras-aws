package com.worker.validador.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@ToString
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;


    private String nome;

    private String produto;

    private BigDecimal valor;

    @JsonFormat(pattern = "YYYY-mm-dd")
    private Date dataCompra;

    private String cpfCliente;

    private String cep;

    private String email;

    private Cartao cartao;




}