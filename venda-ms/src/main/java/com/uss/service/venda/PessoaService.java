package com.uss.service.venda;

import com.uss.entity.venda.PessoaEntity;
import com.uss.to.venda.PessoaTO;

/**
 * Created by ulisses on 17/05/2023.
 */
public interface PessoaService {

    PessoaTO consultarPorCpf(String cpf);
    PessoaEntity incluirAlterar(PessoaTO pessoaTO);
}
