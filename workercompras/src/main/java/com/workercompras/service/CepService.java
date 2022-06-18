package com.workercompras.service;

import com.workercompras.model.Endereco;
import com.workercompras.repository.CepRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CepService {

    private final CepRepository cepRepository;

    public void buscarCep(String cep){

        Endereco endereco = cepRepository.buscarPorCep(cep);
        System.out.println(endereco);

    }



}
