package com.uss.service.venda.impl;

import com.uss.entity.produto.ProdutoEntity;
import com.uss.entity.venda.VendaProdutoEntity;
import com.uss.entity.venda.VendaProdutoItemEntity;
import com.uss.exception.ExceptionValidacao;
import com.uss.repository.produto.ProdutoRepository;
import com.uss.repository.venda.VendaProdutoRepository;
import com.uss.service.venda.PessoaService;
import com.uss.service.venda.VendaProdutoService;
import com.uss.to.venda.VendaProdutoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;

/**
 * Created by ulisses on 16/05/2023.
 */
@Service
public class VendaProdutoServiceImpl implements VendaProdutoService{

    @Autowired
    private VendaProdutoRepository vendaProdutoRepository;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private ProdutoRepository produtoRepository;

    public VendaProdutoTO incluir(VendaProdutoTO vendaProduto){
        return new VendaProdutoTO(this.vendaProdutoRepository.save(criarVendaProdutoEntity(vendaProduto)));
    }

    private VendaProdutoEntity criarVendaProdutoEntity(VendaProdutoTO vendaProduto){
        VendaProdutoEntity vendaProdutoEntity = new VendaProdutoEntity();
        vendaProdutoEntity.setPessoa(this.pessoaService.incluirAlterar(vendaProduto.getPessoa()));
        vendaProdutoEntity.setItens(new HashSet<>());
        vendaProdutoEntity.setDataVenda(LocalDateTime.now());
        vendaProduto.getItens().forEach(vendaProdutoItemTO ->{
            ProdutoEntity produtoEntity = produtoRepository.findById(vendaProdutoItemTO.getProduto().getId()).orElseThrow((()->new ExceptionValidacao("Não foi encontrado produto com id:" + vendaProdutoItemTO.getProduto().getId())));

            VendaProdutoItemEntity item = new VendaProdutoItemEntity();
            item.setProduto(produtoEntity);
            item.setVendaProduto(vendaProdutoEntity);
            item.setQuantidade(vendaProdutoItemTO.getQuantidade());
            item.setValorUnitario(produtoEntity.getValorVigente());
            item.setTotal(item.getValorUnitario().multiply(new BigDecimal(item.getQuantidade())));
            item.setDesconto(vendaProdutoItemTO.getDesconto());
            item.setValorTotal(item.getTotal().subtract(item.getDesconto()));
            if ((item.getDesconto().compareTo(item.getValorTotal()) > 0) ){
                throw new ExceptionValidacao("O valor de desconto não pode ser maior que o valor total");
            }
            vendaProdutoEntity.setValor(vendaProdutoEntity.getValor().add(item.getTotal()));
            vendaProdutoEntity.setDesconto(vendaProdutoEntity.getDesconto().add(item.getDesconto()));

            vendaProdutoEntity.getItens().add(item);
        });
        vendaProdutoEntity.setValorTotal(vendaProdutoEntity.getValor().subtract(vendaProdutoEntity.getDesconto()));
        return vendaProdutoEntity;
    }




    public void excluir(Integer idVendaProduto){
        VendaProdutoEntity vendaProdutoEntity = this.vendaProdutoRepository.findById(idVendaProduto).orElseThrow(()->new ExceptionValidacao("Não existe venda de produto com o id=" + idVendaProduto));
        this.vendaProdutoRepository.delete(vendaProdutoEntity);

    }

}
