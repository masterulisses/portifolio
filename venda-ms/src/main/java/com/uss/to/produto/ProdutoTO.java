package com.uss.to.produto;

import com.uss.entity.produto.ProdutoEntity;
import com.uss.to.SuperTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ulisses on 15/05/2023.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoTO extends SuperTO {

    private Integer id;
    private Integer nsu;
    private String descricaoCurta;
    private String descricaoDetalhada;
    private String pathImagem;
    private List<ProdutoVigenciaTO> vigencias;
    private LocalDateTime dataCadastro;


    public ProdutoTO(ProdutoEntity produtoEntity) {
        this.id = produtoEntity.getId();
        this.nsu = produtoEntity.getNsu();
        this.descricaoCurta = produtoEntity.getDescricaoCurta();
        this.descricaoDetalhada = produtoEntity.getDescricaoDetalhada();
        this.pathImagem = produtoEntity.getPathImagem();
        this.dataCadastro = produtoEntity.getDatacadastro();
        this.vigencias = new ArrayList<>();
        produtoEntity.getVigencias().forEach(produtoVigenciaEntity->{
            this.vigencias.add(new ProdutoVigenciaTO(produtoVigenciaEntity));
        });
    }

}
