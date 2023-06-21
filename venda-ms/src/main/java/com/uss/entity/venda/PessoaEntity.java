package com.uss.entity.venda;

import com.uss.entity.SuperEntity;
import com.uss.to.venda.PessoaTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by ulisses on 16/05/2023.
 */
@Entity
@Table(name="vd_pessoa")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaEntity  extends SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private String celular;
    private String endereco;
    private String complemento;
    private String numero;
    private String bairro;
    private String cep;
    private LocalDateTime datacadastro;


    public PessoaEntity(PessoaTO pessoaTO){
        this.nome = pessoaTO.getNome();
        this.cpf = pessoaTO.getCpf();
        this.dataNascimento = pessoaTO.getDataNascimento();
        this.email = pessoaTO.getEmail();
        this.celular = pessoaTO.getCelular();
        this.endereco = pessoaTO.getEndereco();
        this.complemento = pessoaTO.getComplemento();
        this.numero = pessoaTO.getNumero();
        this.bairro = pessoaTO.getBairro();
        this.cep = pessoaTO.getCep();
        this.datacadastro = LocalDateTime.now();
    }


}
