package com.chekcout.mscompras.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.xml.crypto.Data;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Pedido {

    private String nome;
    private String produto;
    private BigDecimal valor;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Data dataCompra;
    private String cpfCliente;
    private String cep;
}
