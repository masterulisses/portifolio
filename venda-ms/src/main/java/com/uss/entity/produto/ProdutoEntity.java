package com.uss.entity.produto;

import com.uss.entity.SuperEntity;
import com.uss.exception.ExceptionValidacao;
import com.uss.to.produto.ProdutoTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by ulisses on 09/05/2023.
 */
@Entity
@Table(name="vd_produto")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoEntity extends SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer nsu;
    private String descricaoCurta;
    private String descricaoDetalhada;
    private String pathImagem;
    private LocalDateTime datacadastro;

    @OneToMany(fetch= FetchType.EAGER, mappedBy="produto", orphanRemoval=true ,cascade = CascadeType.ALL)
    private Set<ProdutoVigenciaEntity> vigencias;

    public ProdutoEntity(ProdutoTO produtoTO){
        this.nsu = produtoTO.getId();
        this.descricaoCurta = produtoTO.getDescricaoCurta();
        this.descricaoDetalhada = produtoTO.getDescricaoDetalhada();
        this.pathImagem = produtoTO.getPathImagem();
        this.datacadastro = LocalDateTime.now();
        this.vigencias = new HashSet<>();
        produtoTO.getVigencias().forEach(produtoVigenciaVO -> this.vigencias.add(new ProdutoVigenciaEntity(this,produtoVigenciaVO)));
    }


    public BigDecimal getValorVigente()throws ExceptionValidacao{
        LocalDateTime dataAtual = LocalDateTime.now();
        List<ProdutoVigenciaEntity> lista = vigencias.stream().filter(produtoVigenciaEntity -> (dataAtual.isAfter(produtoVigenciaEntity.getVigenciaInicial()) ||
                dataAtual.isEqual(produtoVigenciaEntity.getVigenciaInicial())) &&
                (dataAtual.isBefore(produtoVigenciaEntity.getVigenciaFinal()) ||
                        dataAtual.isEqual(produtoVigenciaEntity.getVigenciaFinal()))
        ).collect(Collectors.toList());
        if ((lista == null) || (lista.isEmpty())){
            throw new ExceptionValidacao("Não tem nenhum valor vigente para o produto=" + this.descricaoCurta);
        }
        return lista.get(0).getValor();
    }





}
