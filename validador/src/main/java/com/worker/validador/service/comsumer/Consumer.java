package com.worker.validador.service.comsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.worker.validador.exceptions.LimiteIndisponivelException;
import com.worker.validador.exceptions.SaldoInsuficienteException;
import com.worker.validador.model.Pedido;
import com.worker.validador.service.ValidadorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor (onConstructor = @__(@Autowired))
@Slf4j
public class Consumer {

    private final ObjectMapper objectMapper;

    private final ValidadorService validadorService;

    @RabbitListener(queues = {"${queue.name}"})
    public void consumer(@Payload Message payload) throws IOException {
        var pedido = objectMapper.readValue(payload.getBody(), Pedido.class);
        log.info("Pedido recebido no validador: {}", pedido);

        try {
            validadorService.validarPedido(pedido);
        } catch (SaldoInsuficienteException | LimiteIndisponivelException e) {
           e.printStackTrace();
        }
    }




}
