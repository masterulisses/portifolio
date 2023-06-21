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
        PessoaEntity pessoaEntity = this.pessoaRepository.findByCpf(pessoaTO.getCpf()).orElse(null);
        if (pessoaEntity == null){
            pessoaEntity = new PessoaEntity(pessoaTO);
        }else{
            pessoaEntity.setNome(pessoaTO.getNome());
            pessoaEntity.setCpf(pessoaTO.getCpf());
            pessoaEntity.setDataNascimento(pessoaTO.getDataNascimento());
            pessoaEntity.setEmail(pessoaTO.getEmail());
            pessoaEntity.setCelular(pessoaTO.getCelular());
            pessoaEntity.setEndereco(pessoaTO.getEndereco());
            pessoaEntity.setComplemento(pessoaTO.getComplemento());
            pessoaEntity.setNumero(pessoaTO.getNumero());
            pessoaEntity.setBairro(pessoaTO.getBairro());
            pessoaEntity.setCep(pessoaTO.getCep());
            pessoaEntity.setDataNascimento(pessoaTO.getDataNascimento());
        }
        return this.pessoaRepository.save(pessoaEntity);

    }


}
