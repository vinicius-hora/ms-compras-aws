package com.chekcout.mscompras.service.rabbit;

import com.chekcout.mscompras.model.Pedido;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.impl.AMQImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
public class Producer {

    private final RabbitTemplate RabbitTemplate;

    private final Queue queue;

    private final ObjectMapper objectMapper;

    @PostMapping
    public void enviarPedido(Pedido payload) {
        try {
            RabbitTemplate.convertAndSend(queue.getName(), objectMapper.writeValueAsString(payload));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
