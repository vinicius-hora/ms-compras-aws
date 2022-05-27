package com.chekcout.mscompras.service;

import com.chekcout.mscompras.model.Pedido;
import com.chekcout.mscompras.repository.PedidoRepository;
import com.chekcout.mscompras.service.rabbit.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final Producer producer;

    public Pedido salvar(Pedido pedido) {
        pedido = pedidoRepository.save(pedido);
        producer.enviarPedido(pedido);
        return pedido;
    }
}
