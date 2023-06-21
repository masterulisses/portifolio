package com.uss.controller.venda;

import com.uss.controller.SuperController;
import com.uss.service.venda.PessoaService;
import com.uss.to.venda.PessoaTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ulisses on 17/05/2023.
 */
@RestController
@RequestMapping("/pessoa")
public class PessoaController extends SuperController {


    @Autowired
    private PessoaService pessoaService;

    @Operation(summary = "Consultar pessoa pelo cpf")
    @GetMapping("/{cpf}")
    public ResponseEntity<PessoaTO> consultarPorCpf(@Parameter(description = "Cpf da pessoa",  example = "316.297.240-09") @PathVariable("cpf") final String cpf) {
        return OK(this.pessoaService.consultarPorCpf(cpf));
    }

}
