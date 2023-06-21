package com.uss.repository.produto;

import com.uss.entity.produto.ProdutoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * Created by ulisses on 09/05/2023.
 */
public interface ProdutoRepository extends PagingAndSortingRepository<ProdutoEntity, Integer> {

    Page<ProdutoEntity> findByDescricaoCurtaIgnoreCaseContaining(String descricaoCurta, Pageable pageable);
    Optional<ProdutoEntity> findByNsu(Integer nsu);
}
