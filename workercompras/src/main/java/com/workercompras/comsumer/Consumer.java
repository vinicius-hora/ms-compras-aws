package com.workercompras.comsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workercompras.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Consumer {

    private final ObjectMapper objectMapper;

    @RabbitListener(queues = {"${queue.name}"})
    public void consumer(@Payload Message payload) throws IOException {
        var pedido = objectMapper.readValue(payload.getBody(), Pedido.class);
        System.out.println("mensagem recebida: " + pedido);
    }


}
