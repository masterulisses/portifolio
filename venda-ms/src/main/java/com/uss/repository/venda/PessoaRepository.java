package com.uss.repository.venda;

import com.uss.entity.venda.PessoaEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * Created by ulisses on 16/05/2023.
 */
public interface PessoaRepository extends PagingAndSortingRepository<PessoaEntity, Integer> {

    Optional<PessoaEntity> findByCpf(String cpf);
}
