package com.uss.entity;

import com.uss.to.ProdutoTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ulisses on 09/05/2023.
 */
@Entity
@Table(name="pr_produto")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoEntity extends SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricaoCurta;
    private String descricaoDetalhada;
    private String pathImagem;
    private LocalDateTime datacadastro;

    @OneToMany(fetch= FetchType.LAZY, mappedBy="produto", orphanRemoval=true ,cascade = CascadeType.ALL)
    private Set<ProdutoVigenciaEntity> vigencias;


    public ProdutoEntity(ProdutoTO produtoTO){
        this.descricaoCurta = produtoTO.getDescricaoCurta();
        this.descricaoDetalhada = produtoTO.getDescricaoDetalhada();
        this.pathImagem = produtoTO.getPathImagem();
        this.datacadastro = LocalDateTime.now();
        this.vigencias = new HashSet<>();
        produtoTO.getVigencias().forEach(produtoVigenciaVO -> this.vigencias.add(new ProdutoVigenciaEntity(this,produtoVigenciaVO)));
    }


}
