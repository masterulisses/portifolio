package com.uss.entity.venda;

import com.uss.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by ulisses on 16/05/2023.
 */
@Entity
@Table(name="vd_venda_produto")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendaProdutoEntity extends SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch= FetchType.LAZY)
    private PessoaEntity pessoa;
    private BigDecimal valor = new BigDecimal(0);
    private BigDecimal desconto= new BigDecimal(0);;
    private BigDecimal valorTotal;
    private LocalDateTime dataVenda;

    @OneToMany(fetch= FetchType.LAZY, mappedBy="vendaProduto", orphanRemoval=true ,cascade = CascadeType.ALL)
    private Set<VendaProdutoItemEntity> itens;



}
