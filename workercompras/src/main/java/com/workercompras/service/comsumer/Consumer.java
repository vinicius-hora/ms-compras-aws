package com.workercompras.service.comsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workercompras.model.Pedido;
import com.workercompras.service.EmailService;
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
    private final EmailService emailService;

    @RabbitListener(queues = {"${queue.name}"})
    public void consumer(@Payload Message payload) throws IOException {
        var pedido = objectMapper.readValue(payload.getBody(), Pedido.class);
        log.info("Pedido recebido: {}", pedido);
        emailService.notificarCliente(pedido);
    }


}
