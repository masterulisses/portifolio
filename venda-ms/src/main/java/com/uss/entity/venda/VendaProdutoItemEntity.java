package com.uss.entity.venda;

import com.uss.entity.produto.ProdutoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by ulisses on 16/05/2023.
 */
@Entity
@Table(name="vd_venda_produto_item")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendaProdutoItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch= FetchType.LAZY)
    private VendaProdutoEntity vendaProduto;
    @ManyToOne(fetch= FetchType.LAZY)
    private ProdutoEntity produto;
    private BigDecimal valorUnitario;
    private int quantidade;
    private BigDecimal total;
    private BigDecimal desconto;
    private BigDecimal valorTotal;

    @Override
    public int hashCode() {
        return  31 *  ((produto == null) ? 0 : produto.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if ((!(obj instanceof VendaProdutoItemEntity)) || (obj == null)) {
            return false;
        }
        VendaProdutoItemEntity produtoVigenciaEntity =(VendaProdutoItemEntity)obj;
        if (produtoVigenciaEntity.getId() == null){
            return false;
        }
        return produtoVigenciaEntity.getId().equals(this.id);
    }

}
