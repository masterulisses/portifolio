package com.uss.to.produto;

import com.uss.entity.produto.ProdutoVigenciaEntity;
import com.uss.to.SuperTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by ulisses on 15/05/2023.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoVigenciaTO extends SuperTO {

    private Integer id;
    private BigDecimal valor;
    private LocalDateTime vigenciaInicial;
    private LocalDateTime vigenciaFinal;
    private LocalDateTime dataCadastro;
    private Integer nsu;



    public ProdutoVigenciaTO(ProdutoVigenciaEntity produtoVigenciaEntity){
        this.id = produtoVigenciaEntity.getId() ;
        this.valor = produtoVigenciaEntity.getValor();
        this.vigenciaInicial = produtoVigenciaEntity.getVigenciaInicial();
        this.vigenciaFinal = produtoVigenciaEntity.getVigenciaFinal();
        this.dataCadastro = produtoVigenciaEntity.getDataCadastro();
        this.nsu = produtoVigenciaEntity.getNsu();
    }

    public Integer getNsu(){
        if (this.nsu == null){
            this.nsu = this.id;
        }
        return this.nsu;
    }



    @Override
    public int hashCode() {
        return  31 *  ((valor == null) ? 0 : valor.hashCode());
    }



    @Override
    public boolean equals(Object obj) {
        if ((!(obj instanceof ProdutoVigenciaTO)) || (obj == null)) {
            return false;
        }
        ProdutoVigenciaTO produtoVigenciaTO = (ProdutoVigenciaTO)obj;
        if (produtoVigenciaTO.getNsu() != null){
            return produtoVigenciaTO.getNsu().equals(this.nsu);
        }
        return this.valor.equals(produtoVigenciaTO.getValor()) && this.vigenciaInicial.equals(produtoVigenciaTO.getVigenciaInicial());
    }

}
