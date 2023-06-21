package com.uss.service.produto;

import com.uss.to.produto.ProdutoTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by ulisses on 09/05/2023.
 */
public interface ProdutoService {

    ProdutoTO consultarPorId(Integer id);
    Page<ProdutoTO> consultarPorDescricao(String descricaoCurta, Pageable pageable);
    ProdutoTO incluirAlterar(ProdutoTO produtoTO);
}
