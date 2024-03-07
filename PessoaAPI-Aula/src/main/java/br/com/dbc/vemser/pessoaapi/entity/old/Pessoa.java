package br.com.dbc.vemser.pessoaapi.entity;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.time.LocalDate;
@Component
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pessoa {
    private Integer idPessoa;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;


}
