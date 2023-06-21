package com.uss.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by ulisses on 10/05/2023.
 */
@Data
@Builder
@AllArgsConstructor
public class PaginadorTO {

    private int paginaAtual;
    private long qtdePorPagina;
    private long totalPaginas;
    private boolean primeiraPagina=false;
    private boolean ultimaPagina=false;
    private List dados;

    public PaginadorTO(Page page){
        this.totalPaginas = page.getTotalPages();
        this.paginaAtual = page.getPageable().getPageNumber();
        this.qtdePorPagina = page.getPageable().getPageSize();
        this.primeiraPagina = page.isFirst();
        this.ultimaPagina = page.isLast();
        this.dados = page.getContent();
    }
}
