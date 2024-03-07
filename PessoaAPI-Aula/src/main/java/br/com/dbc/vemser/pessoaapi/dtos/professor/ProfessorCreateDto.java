package br.com.dbc.vemser.pessoaapi.dtos.professor;

import br.com.dbc.vemser.pessoaapi.entity.pk.ProfessorPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorCreateDto {


    @NotNull
    private ProfessorPK professorPK;

    @NotNull
    private String nome;

    @NotNull
    private Long salario;
}
