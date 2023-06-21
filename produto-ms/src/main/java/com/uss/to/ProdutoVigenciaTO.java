package com.uss.to;

import com.uss.entity.ProdutoVigenciaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by ulisses on 15/05/2023.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ScriptAssert(lang = "javascript", script = "_this.vigenciaInicial == null || _this.vigenciaFinal == null || _this.vigenciaInicial.isBefore(_this.vigenciaFinal)",message="A vigência inicial deve ser menor que a vigência final" )
public class ProdutoVigenciaTO extends SuperTO {

    private Integer id;
    @NotNull(message = "O campo valor é obrigatório.")
    @Positive(message = "O campo valor não é válido.")
    @Min(value = 1, message = "O valor mínimo deve ser 1.")
    private BigDecimal valor;
    @NotNull(message = "O campo vigência inicial é obrigatório.")
    @FutureOrPresent(message = "A vigência inicial deve ser maior ou igual à data atual.")
    private LocalDateTime vigenciaInicial;

    @NotNull(message = "O campo vigência final é obrigatório.")
    @FutureOrPresent(message = "A vigência final deve ser maior ou igual à data atual.")
    private LocalDateTime vigenciaFinal;
    private LocalDateTime dataCadastro;



    public ProdutoVigenciaTO(ProdutoVigenciaEntity produtoVigenciaEntity){
        this.id = produtoVigenciaEntity.getId() ;
        this.valor = produtoVigenciaEntity.getValor();
        this.vigenciaInicial = produtoVigenciaEntity.getVigenciaInicial();
        this.vigenciaFinal = produtoVigenciaEntity.getVigenciaFinal();
        this.dataCadastro = produtoVigenciaEntity.getDataCadastro();
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
        if (produtoVigenciaTO.getId() != null){
            return produtoVigenciaTO.getId().equals(this.id);
        }
        return this.valor.equals(produtoVigenciaTO.getValor()) && this.vigenciaInicial.equals(produtoVigenciaTO.getVigenciaInicial());
    }

}
