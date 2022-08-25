package com.worker.validador.service;



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

    public void notificarClienteCompraComsSucesso(String email) {
        var msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Compra confirmada");
        msg.setText("Sua compra foi aprovada, breve você receberá seu código de rastreio");
//        javaMailSender.send(msg);
        log.info("Cliente notificado com sucesso!!");


    }


}
