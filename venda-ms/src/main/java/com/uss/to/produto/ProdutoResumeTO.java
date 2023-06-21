package com.uss.to.produto;

import com.uss.to.SuperTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Created by ulisses on 16/05/2023.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResumeTO extends SuperTO {

    @NotNull(message = "O campo id do produto é obrigatório.")
    @Positive(message = "O campo id do produto não é válido.")
    private Integer id;

}
