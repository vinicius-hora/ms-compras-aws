package com.chekcout.mscompras.controller;

import com.chekcout.mscompras.DadosMock;
import com.chekcout.mscompras.exception.serviceexception.EntidadeNaoEncontradaException;
import com.chekcout.mscompras.exception.serviceexception.NegocioException;
import com.chekcout.mscompras.model.Pedido;
import com.chekcout.mscompras.service.PedidoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.chekcout.mscompras.MscomprasApplication;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MscomprasApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
 class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PedidoService pedidoService;

    private DadosMock dadosMock = new DadosMock();

    @Autowired
    private ObjectMapper mapper;

    private static final String ROTA_PEDIDO = "/pedido";

    @Test
    @DisplayName("POST - deve cadastar um novo pedido com sucesso no banco")
    void  deveCadastrarPedidoComSucesso() throws Exception {

        var pedidoBody = dadosMock.getPedido();
        var id = 2L;

        mockMvc.perform(post(ROTA_PEDIDO + "/")
                .content(mapper.writeValueAsString(pedidoBody))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        Pedido pedidoSalvo =  pedidoService.buscarOuFalharPorId(id);
        assertEquals(pedidoSalvo.getId(), id);
        assertNotNull(pedidoSalvo);


    }

    @DisplayName("GET- deve buscar o pedido com sucesso")
    @Test
    void deveBuscarPedidoPorId() throws Exception {
        var id = 2L;
        mockMvc.perform(get(ROTA_PEDIDO + "/" + id))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @DisplayName("GET - falhar ao buscar pedido que não existe")
    @Test
    void deveFalharAoBuscarPedidoPorId() throws Exception {
        var id = 2L;
        mockMvc.perform(get(ROTA_PEDIDO + "/" + id))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @DisplayName("DELETE - excluir um pedido")
    @Test
    void deveExcluirPedidoComSucesso() throws Exception {
        var id = 1L;
        mockMvc.perform(delete(ROTA_PEDIDO + "/" + id))
                .andDo(print())
                .andExpect(status().isNoContent());

        Throwable exception = assertThrows(EntidadeNaoEncontradaException.class, () ->{
            pedidoService.buscarOuFalharPorId(id);
        });

        assertEquals("O pedido de id: " + id + " não existe na base de dados", exception.getMessage());

    }
}
