package com.uss.repository.venda;

import com.uss.entity.venda.VendaProdutoEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by ulisses on 16/05/2023.
 */
public interface VendaProdutoRepository extends PagingAndSortingRepository<VendaProdutoEntity, Integer> {
}
