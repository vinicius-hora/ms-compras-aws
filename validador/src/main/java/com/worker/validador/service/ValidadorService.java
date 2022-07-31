package com.worker.validador.service;

import com.worker.validador.exceptions.LimiteIndisponivelException;
import com.worker.validador.exceptions.SaldoInsuficienteException;
import com.worker.validador.model.Cartao;
import com.worker.validador.model.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ValidadorService {

    private final EmailService emailService;
    public void validarPedido(Pedido pedido){

        validarLimiteDisponivel(pedido.getCartao());
        validarCompraComLimite(pedido);
        emailService.notificarClienteCompraComsSucesso(pedido.getEmail());
    }

    private void validarCompraComLimite(Pedido pedido)  {
        if(pedido.getValor().longValue() > pedido.getCartao().getLimiteDisponivel().longValue()){
            log.error("valor do pedido: {}. limite disponivel: {}", pedido.getValor(), pedido.getCartao().getLimiteDisponivel());
            throw new SaldoInsuficienteException("Você não tem limite para efetuar essa compra!");
        }
    }

    private void validarLimiteDisponivel(Cartao cartao) {
        if (cartao.getLimiteDisponivel().longValue() <= 0){
            throw new LimiteIndisponivelException("Limite indisponível!");
        }
    }


}
