package com.uss.controller;

import com.uss.to.PaginadorTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ulisses on 10/05/2023.
 */
public class SuperController {

    public ResponseEntity OK(Object object){
        if (object instanceof Page){
           object = new PaginadorTO((Page)object);
        }
        return new ResponseEntity(object, HttpStatus.OK);
    }

    public ResponseEntity OKExclusao(){
        return new ResponseEntity("Registro excluído com sucesso.", HttpStatus.OK);
    }


    public Pageable getPageable(Integer paginaAtual, Integer qtdePorPagina, String[] sort){
        List<Sort.Order> orders = convertOrders(sort);
        return PageRequest.of(paginaAtual,qtdePorPagina, Sort.by(orders));
    }

    private List<Sort.Order> convertOrders(String[] sort){
        List<Sort.Order> orders = new ArrayList<>();
        if ((sort != null) && (sort.length >0)) {
            if (sort[0].contains(",")) {
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(Sort.Direction.valueOf(_sort[1].toUpperCase()), _sort[0]));
                }
            } else {
                orders.add(new Sort.Order(Sort.Direction.valueOf(sort[1].toUpperCase()) , sort[0]));
            }
        }
        return orders;

    }
}
