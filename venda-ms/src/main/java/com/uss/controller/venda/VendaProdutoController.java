package com.uss.controller.venda;

import com.uss.controller.SuperController;
import com.uss.service.venda.VendaProdutoService;
import com.uss.to.produto.ProdutoTO;
import com.uss.to.venda.VendaProdutoTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by ulisses on 16/05/2023.
 */
@RestController
@RequestMapping("/vendaProduto")
public class VendaProdutoController extends SuperController {

    // http://localhost:8081/venda-ms/swagger-ui/index.html

    @Autowired
    private VendaProdutoService vendaProdutoService;

    @Operation(summary = "Incluir nova venda de produto")
    @PostMapping
    public ResponseEntity<ProdutoTO> incluir(@Valid @RequestBody final VendaProdutoTO vendaProduto) {
        return OK(this.vendaProdutoService.incluir(vendaProduto));
    }

    @Operation(summary = "Excluir venda de produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@Parameter(description = "Id da venda do produto",  example = "1") @PathVariable("id") final Integer id) {
        this.vendaProdutoService.excluir(id);
        return OKExclusao();
    }


}
