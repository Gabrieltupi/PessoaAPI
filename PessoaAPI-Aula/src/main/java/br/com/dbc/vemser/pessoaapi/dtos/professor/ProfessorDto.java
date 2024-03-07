package br.com.dbc.vemser.pessoaapi.dtos.professor;

import br.com.dbc.vemser.pessoaapi.entity.pk.ProfessorPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDto {

    @NotNull
    private ProfessorPK professorPK;

    @NotNull
    private String nome;

    @NotBlank
    private Long salario;



}

