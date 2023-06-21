package com.uss.to;

import com.uss.entity.ProdutoEntity;
import com.uss.entity.ProdutoVigenciaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "O campo descricaoCurta é obrigatório.")
    private String descricaoCurta;
    private String descricaoDetalhada;
    private String pathImagem;
    @Valid
    private List<ProdutoVigenciaTO> vigencias;

    private LocalDateTime dataCadastro;

    public ProdutoTO(ProdutoEntity produtoEntity) {
        this.id = produtoEntity.getId();
        this.descricaoCurta = produtoEntity.getDescricaoCurta();
        this.descricaoDetalhada = produtoEntity.getDescricaoDetalhada();
        this.pathImagem = produtoEntity.getPathImagem();
        this.dataCadastro = produtoEntity.getDatacadastro();
        this.vigencias = new ArrayList<>();
        for (ProdutoVigenciaEntity produtoVigenciaEntity: produtoEntity.getVigencias()){
            this.vigencias.add(new ProdutoVigenciaTO(produtoVigenciaEntity));
        };
    }

}
