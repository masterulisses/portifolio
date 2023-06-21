package com.uss.controller;

import com.uss.kafka.KafkaProducer;
import com.uss.service.ProdutoService;
import com.uss.to.PaginadorTO;
import com.uss.to.ProdutoTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by ulisses on 09/05/2023.
 */
@RestController
@RequestMapping("/produto")
public class ProdutoController extends SuperController {

    // http://localhost:8082/produto-ms/swagger-ui/index.html
    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private KafkaProducer kafkaProducer2;

    //http://localhost:8082/produto-ms/produto/2
    @Operation(summary = "Consultar Produto por id")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoTO> consultarPorId(@Parameter(description = "Id do produto",  example = "1") @PathVariable("id") final Integer id) {
        return OK(this.produtoService.consultarPorId(id));
    }

    @Operation(summary = "Consultar Produto pela descricaoCurta")
    @GetMapping
    public ResponseEntity<PaginadorTO> consultarPorDescricaoCurta(@RequestParam(required = false) @Parameter(description = "descrição parcial do produto com ou sem acentuação",  example = "Arroz") String descricaoCurta,
                                                                  @Parameter(name = "paginaAtual", description = "Número da página atual", example = "0") @RequestParam(defaultValue = "0") Integer paginaAtual,
                                                                  @Parameter(name = "qtdePorPagina", description = "Quantidade de resultados por página", example = "5") @RequestParam(defaultValue = "5") Integer qtdePorPagina,
                                                                  @Parameter(name = "ordenacao", description = "Nome da coluna a ser ordenada e direção", example = "descricaoCurta,asc") @RequestParam(defaultValue = "descricaoCurta,asc") String[] ordenacao){


        return OK(this.produtoService.consultarPorDescricao(descricaoCurta, getPageable(paginaAtual, qtdePorPagina, ordenacao)));
    }

    @Operation(summary = "Incluir novo produto")
    @PostMapping
    public ResponseEntity<ProdutoTO> incluir(@Valid @RequestBody final ProdutoTO produto) {
        ProdutoTO produtoTO = this.produtoService.incluir(produto);
        this.kafkaProducer2.sendMessage(produtoTO);
        return OK(produtoTO);
    }

    @Operation(summary = "Alterar produto")
    @PutMapping
    public ResponseEntity<ProdutoTO> alterar(@Valid @RequestBody final ProdutoTO produto) {
        ProdutoTO produtoTO = this.produtoService.alterar(produto);
        this.kafkaProducer2.sendMessage(produtoTO);
        return OK(produtoTO);
    }

    @Operation(summary = "Excluir produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@Parameter(description = "Id do produto",  example = "1") @PathVariable("id") final Integer id) {
        this.produtoService.excluir(id);
        return OKExclusao();
    }

}

