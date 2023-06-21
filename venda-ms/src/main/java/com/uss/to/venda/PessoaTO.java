package com.uss.to.venda;

import com.uss.entity.venda.PessoaEntity;
import com.uss.to.SuperTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by ulisses on 16/05/2023.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaTO extends SuperTO {

    private Integer id;
    @NotNull(message = "O campo nome da pessoa é obrigatório.")
    @NotEmpty(message = "O campo nome da pessoa é obrigatório.")
    private String nome;
    @NotEmpty(message = "O campo CPF da pessoa é obrigatório.")
    @CPF(message = "O campo CPF da pessoa não é válido.")
    private String cpf;
    private LocalDate dataNascimento;
    @NotNull(message = "O campo email da pessoa é obrigatório.")
    @NotEmpty(message = "O campo email da pessoa é obrigatório.")
    @Email
    private String email;
    private String celular;
    private String endereco;
    private String complemento;
    private String numero;
    private String bairro;
    private String cep;
    private LocalDateTime datacadastro;

    public PessoaTO(PessoaEntity pessoaEntity){
        this.id = pessoaEntity.getId();
        this.nome = pessoaEntity.getNome();
        this.cpf = pessoaEntity.getCpf();
        this.dataNascimento = pessoaEntity.getDataNascimento();
        this.email = pessoaEntity.getEmail();
        this.celular = pessoaEntity.getCelular();
        this.endereco = pessoaEntity.getEndereco();
        this.complemento = pessoaEntity.getComplemento();
        this.numero = pessoaEntity.getNumero();
        this.bairro = pessoaEntity.getBairro();
        this.cep = pessoaEntity.getCep();
        this.datacadastro = pessoaEntity.getDatacadastro();
    }


}
