package com.uss.entity;

import com.uss.to.ProdutoVigenciaTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by ulisses on 10/05/2023.
 */
@Entity
@Table(name="pr_produtovigencia")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVigenciaEntity extends SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch= FetchType.LAZY)
    private ProdutoEntity produto;

    private BigDecimal valor;
    private LocalDateTime vigenciaInicial;
    private LocalDateTime vigenciaFinal;
    private LocalDateTime dataCadastro;

    public ProdutoVigenciaEntity(ProdutoEntity produtoEntity, ProdutoVigenciaTO produtoVigenciaTO){
        this.produto = produtoEntity;
        this.valor = produtoVigenciaTO.getValor();
        this.vigenciaInicial = produtoVigenciaTO.getVigenciaInicial();
        this.vigenciaFinal = produtoVigenciaTO.getVigenciaFinal();
        this.dataCadastro = LocalDateTime.now();
    }



    @Override
    public int hashCode() {
        return  31 *  ((valor == null) ? 0 : valor.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if ((!(obj instanceof ProdutoVigenciaEntity)) || (obj == null)) {
            return false;
        }
        ProdutoVigenciaEntity produtoVigenciaEntity =(ProdutoVigenciaEntity)obj;
        if (produtoVigenciaEntity.getId() == null){
            return false;
        }
        return produtoVigenciaEntity.getId().equals(this.id);
    }


}
