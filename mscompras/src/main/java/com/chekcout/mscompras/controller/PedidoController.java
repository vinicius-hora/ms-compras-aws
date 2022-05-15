package com.chekcout.mscompras.controller;

import com.chekcout.mscompras.model.Pedido;
import com.chekcout.mscompras.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor (onConstructor = @__(@Autowired))
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping("/")
    public ResponseEntity<Pedido> salvar(@RequestBody @Valid Pedido pedido) {
        return ResponseEntity.ok(pedidoService.salvar(pedido));
    }
}
