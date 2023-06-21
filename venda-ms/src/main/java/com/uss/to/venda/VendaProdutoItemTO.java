package com.uss.to.venda;

import com.uss.entity.venda.VendaProdutoItemEntity;
import com.uss.to.SuperTO;
import com.uss.to.produto.ProdutoResumeTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * Created by ulisses on 16/05/2023.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendaProdutoItemTO extends SuperTO {

    private Integer id;
    @Valid
    private ProdutoResumeTO produto;
    private BigDecimal valorUnitario;
    @NotNull(message = "O campo quantidade é obrigatório.")
    @Positive(message = "O campo quantidade não é válido.")
    @Min(value = 1, message = "O valor mínimo da quantidade deve ser 1.")
    private int quantidade;
    private BigDecimal total;
    private BigDecimal desconto=new BigDecimal(0);
    private BigDecimal valorTotal;


    public VendaProdutoItemTO(VendaProdutoItemEntity vendaProdutoItemEntity){
        this.id = vendaProdutoItemEntity.getId();
        this.produto = new ProdutoResumeTO(vendaProdutoItemEntity.getProduto().getId());
        this.valorUnitario = vendaProdutoItemEntity.getValorUnitario();
        this.quantidade = vendaProdutoItemEntity.getQuantidade();
        this.total = vendaProdutoItemEntity.getTotal();
        this.desconto = vendaProdutoItemEntity.getDesconto();
        this.valorTotal = vendaProdutoItemEntity.getValorTotal();
    }



    @Override
    public int hashCode() {
        return  31 *  ((produto == null) ? 0 : produto.getId().hashCode());
    }




    @Override
    public boolean equals(Object obj) {
        if ((!(obj instanceof VendaProdutoItemTO)) || (obj == null)) {
            return false;
        }
        VendaProdutoItemTO vendaProdutoItemTO = (VendaProdutoItemTO)obj;
        if (vendaProdutoItemTO.getId() != null){
            return vendaProdutoItemTO.getId().equals(this.id);
        }
        return this.produto.getId().equals(vendaProdutoItemTO.getProduto().getId());
    }

}
