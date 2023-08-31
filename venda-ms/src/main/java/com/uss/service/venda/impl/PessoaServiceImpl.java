package com.uss.service.venda.impl;

import com.uss.entity.venda.PessoaEntity;
import com.uss.repository.venda.PessoaRepository;
import com.uss.service.venda.PessoaService;
import com.uss.to.venda.PessoaTO;
import com.uss.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ulisses on 17/05/2023.
 */
@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaTO consultarPorCpf(String cpf){
        PessoaEntity pessoaEntity = this.pessoaRepository.findByCpf(Util.formatarCPF(cpf)).orElse(null);
        return pessoaEntity == null? new PessoaTO(): new PessoaTO(pessoaEntity);
    }

    public PessoaEntity incluirAlterar(PessoaTO pessoaTO){
        pessoaTO.setCpf(Util.formatarCPF(pessoaTO.getCpf()));
        PessoaEntity pessoaEntity = this.pessoaRepository.findByCpf(pessoaTO.getCpf())
                .map((pessoa) -> {
                    pessoa.setNome(pessoaTO.getNome());
                    pessoa.setCpf(pessoaTO.getCpf());
                    pessoa.setDataNascimento(pessoaTO.getDataNascimento());
                    pessoa.setEmail(pessoaTO.getEmail());
                    pessoa.setCelular(pessoaTO.getCelular());
                    pessoa.setEndereco(pessoaTO.getEndereco());
                    pessoa.setComplemento(pessoaTO.getComplemento());
                    pessoa.setNumero(pessoaTO.getNumero());
                    pessoa.setBairro(pessoaTO.getBairro());
                    pessoa.setCep(pessoaTO.getCep());
                    pessoa.setDataNascimento(pessoaTO.getDataNascimento());
                    return pessoa;})
                 .orElse(new PessoaEntity(pessoaTO));
        return this.pessoaRepository.save(pessoaEntity);

    }


}
