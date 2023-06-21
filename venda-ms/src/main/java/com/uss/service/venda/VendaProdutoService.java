package com.uss.service.venda;

import com.uss.to.venda.VendaProdutoTO;

/**
 * Created by ulisses on 16/05/2023.
 */

public interface VendaProdutoService {

    VendaProdutoTO incluir(VendaProdutoTO vendaProduto);
    void excluir(Integer idVendaProduto);
}
