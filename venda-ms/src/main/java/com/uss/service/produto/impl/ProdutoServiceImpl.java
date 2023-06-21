package com.uss.service.produto.impl;

import com.uss.entity.produto.ProdutoEntity;
import com.uss.entity.produto.ProdutoVigenciaEntity;
import com.uss.exception.ExceptionValidacao;
import com.uss.repository.produto.ProdutoRepository;
import com.uss.service.produto.ProdutoService;
import com.uss.to.produto.ProdutoTO;
import com.uss.to.produto.ProdutoVigenciaTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by maste on 09/05/2023.
 */
@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    public ProdutoTO consultarPorId(Integer id){
        return new ProdutoTO(this.produtoRepository.findById(id).orElseThrow(()-> new ExceptionValidacao("Não existe produto com o id:" + id)));
    }

    public Page<ProdutoTO> consultarPorDescricao(String descricaoCurta,Pageable pageable){
        Page<ProdutoEntity> pageResults;
        if (StringUtils.isBlank(descricaoCurta)){
            pageResults = this.produtoRepository.findAll(pageable);
        }else{
            pageResults = this.produtoRepository.findByDescricaoCurtaIgnoreCaseContaining(descricaoCurta,pageable);
        }
        return pageResults.map(produtoEntity -> new ProdutoTO(produtoEntity));
    }


    public ProdutoTO incluirAlterar(ProdutoTO produtoTO){
        ProdutoEntity produtoEntity = this.produtoRepository.findByNsu(produtoTO.getId()).orElse(null);
        if (produtoEntity != null){
            alterarDados(produtoEntity,produtoTO);
        }else{
            produtoEntity = new ProdutoEntity(produtoTO);
        }
        return new ProdutoTO(this.produtoRepository.save(produtoEntity));
    }

    private void alterarDados(ProdutoEntity produtoEntity, ProdutoTO produtoTO){
        produtoEntity.setDescricaoCurta(produtoTO.getDescricaoCurta());
        produtoEntity.setDescricaoDetalhada(produtoTO.getDescricaoDetalhada());
        produtoEntity.setPathImagem(produtoTO.getPathImagem());
        alterarDadosVigencia(produtoEntity,produtoTO);
    }

    private void alterarDadosVigencia(ProdutoEntity produtoEntity, ProdutoTO produtoTO){
        if ((produtoTO.getVigencias() == null) || (produtoTO.getVigencias().isEmpty())){
            if (produtoEntity.getVigencias() != null) {
                produtoEntity.getVigencias().clear();
            }
        }else{
            if (produtoEntity.getVigencias() == null){
                produtoEntity.setVigencias(new HashSet<>());
            }
            // Excluir as vigências.
            produtoEntity.getVigencias().removeAll(produtoEntity.getVigencias().stream().filter(
                    produtoVigenciaEntity -> !produtoTO.getVigencias().contains(new ProdutoVigenciaTO(produtoVigenciaEntity))).collect(Collectors.toList()
            ));
            // alterar/incluir vigência.
            for(ProdutoVigenciaTO produtoVigenciaTO: produtoTO.getVigencias()){
                boolean alterou= false;
                for (ProdutoVigenciaEntity produtoVigenciaEntity: produtoEntity.getVigencias()){
                    if ((produtoVigenciaEntity.getNsu() != null) &&  (produtoVigenciaTO.getId() != null) && produtoVigenciaEntity.getNsu().equals(produtoVigenciaTO.getId())){
                        produtoVigenciaEntity.setValor(produtoVigenciaTO.getValor());
                        produtoVigenciaEntity.setVigenciaInicial(produtoVigenciaTO.getVigenciaInicial());
                        produtoVigenciaEntity.setVigenciaFinal(produtoVigenciaTO.getVigenciaFinal());
                        alterou = true;
                        break;
                    }
                }
                if (!alterou){
                    produtoEntity.getVigencias().add(new ProdutoVigenciaEntity(produtoEntity, produtoVigenciaTO));
                }
            }
        }
    }

}

