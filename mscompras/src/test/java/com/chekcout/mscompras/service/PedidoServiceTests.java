package com.chekcout.mscompras.service;

import com.chekcout.mscompras.DadosMock;
import com.chekcout.mscompras.exception.serviceexception.NegocioException;
import com.chekcout.mscompras.model.Pedido;
import com.chekcout.mscompras.repository.PedidoRepository;
import com.chekcout.mscompras.service.rabbit.Producer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTests {
    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private Producer producer;

    private DadosMock dadosMock = new DadosMock();



    @Test
    @DisplayName("Salvar pedido com sucesso")
    void deveSalvarUmPedidoComSucesso(){
        var pedidoMock = dadosMock.getPedido();

        Mockito.when(pedidoRepository.save(Mockito.any(Pedido.class))).thenReturn(pedidoMock);
        Mockito.doNothing().when(producer).enviarPedido(Mockito.any(Pedido.class));
        Pedido pedidoSalvo = pedidoService.salvar(pedidoMock);

        assertEquals(pedidoMock.getCep(), pedidoSalvo.getCep());
        assertTrue(pedidoSalvo.getCep() != null);



    }

    @Test
    @DisplayName("ddeve falhar na busca de pedido que não existe")
    void deveFalharNaBuscaDePedidoNaoExistente(){

        var id = 1L;

        Throwable exception = assertThrows(NegocioException.class, () ->{
            pedidoService.buscarOuFalharPorId(id);
        });

        assertEquals("O pedido de id: " + id + " não existe na base de dados", exception.getMessage());


    }

    @DisplayName("Deve buscar um pedido com sucesso na base de dados")
    @Test
    void deveBuscarUmPedidoComSucesso(){
        var id = 1L;
        var pedidoMock = dadosMock.getPedidoSalvo();

        Mockito.when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedidoMock));

        var pedidoSalvo = pedidoService.buscarOuFalharPorId(id);

        assertEquals(pedidoMock.getId(), pedidoSalvo.getId());
        assertNotNull(pedidoSalvo);
        Mockito.verify(pedidoRepository, Mockito.atLeastOnce()).findById(id);


    }

    @DisplayName("Deve excluir o pedido com sucesso")
    @Test
    void deveExcluirOPedidoComSucesso(){
        var id = 1L;
        var pedidoMock = dadosMock.getPedidoSalvo();
        Mockito.when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedidoMock));
        Mockito.doNothing().when(pedidoRepository).deleteById(id);

        pedidoService.excluir(id);
        Mockito.verify(pedidoRepository, Mockito.atLeastOnce()).deleteById(id);

    }


    @DisplayName("Deve falhar ao excluir pedido")
    @Test
    void deveFalharaoExcluirPedido(){
        var id = 1L;
        var pedidoMock = dadosMock.getPedidoSalvo();
        Mockito.when(pedidoRepository.findById(id)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(NegocioException.class, () ->{
            pedidoService.excluir(id);
        });

        assertEquals("O pedido de id: " + id + " não existe na base de dados", exception.getMessage());


    }


}
