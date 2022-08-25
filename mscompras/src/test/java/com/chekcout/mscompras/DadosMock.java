package com.chekcout.mscompras;

import com.chekcout.mscompras.model.Pedido;

import java.math.BigDecimal;
import java.util.Date;

public class DadosMock {

    public Pedido getPedido(){
        return Pedido.builder()
                .valor(BigDecimal.TEN)
                .nome("vinicius")
                .produto("1111")
                .dataCompra(new Date())
                .cpfCliente("111111111111")
                .cep("121212121212")
                .email("teste@teste.com")
                .build();

    }

    public Pedido getPedidoSalvo(){
        return Pedido.builder()
                .id(1L)
                .valor(BigDecimal.TEN)
                .nome("vinicius")
                .produto("1111")
                .dataCompra(new Date())
                .cpfCliente("111111111111")
                .cep("121212121212")
                .email("teste@teste.com")
                .build();

    }
}
