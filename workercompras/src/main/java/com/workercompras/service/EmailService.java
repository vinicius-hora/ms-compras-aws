package com.workercompras.service;

import com.workercompras.model.Pedido;
import com.workercompras.service.producer.PedidoProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    private final PedidoProducer pedidoProducer;

    public void notificarCliente(Pedido pedido) {
        var msg = new SimpleMailMessage();
        msg.setTo(pedido.getEmail());
        msg.setSubject("Compra recebida");
        msg.setText("Este é um e-mail de confirmação de compra recebida. " +
                "Agora vamos aprovar sua compra e brevemente você receberá um novo e-mail de confirmação." +
                "\nObrigado por comprar com a gente!!");
//        javaMailSender.send(msg);
        log.info("Cliente notificado com sucesso!!");

        log.info("preparando pedido consumer...");
        pedidoProducer.enviarPedido(pedido);

    }


}
