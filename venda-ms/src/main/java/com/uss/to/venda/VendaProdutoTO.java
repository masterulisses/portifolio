package com.uss.to.venda;

import com.uss.entity.venda.VendaProdutoEntity;
import com.uss.entity.venda.VendaProdutoItemEntity;
import com.uss.to.SuperTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ulisses on 16/05/2023.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendaProdutoTO extends SuperTO {

    private Integer id;
    @Valid
    private PessoaTO pessoa;
    private BigDecimal valor;
    private BigDecimal desconto;
    private BigDecimal valorTotal;
    private LocalDateTime dataVenda;
    @Valid
    private List<VendaProdutoItemTO> itens;

    public VendaProdutoTO(VendaProdutoEntity vendaProdutoEntity){
        this.id = vendaProdutoEntity.getId();
        this.pessoa = new PessoaTO(vendaProdutoEntity.getPessoa());
        this.valor = vendaProdutoEntity.getValor();
        this.desconto = vendaProdutoEntity.getDesconto();
        this.valorTotal = vendaProdutoEntity.getValorTotal();
        this.dataVenda = vendaProdutoEntity.getDataVenda();
        this.itens = new ArrayList<>();
        vendaProdutoEntity.getItens().forEach(vendaProdutoItemEntity->{
            this.itens.add(new VendaProdutoItemTO(vendaProdutoItemEntity));
        });

    }
}
