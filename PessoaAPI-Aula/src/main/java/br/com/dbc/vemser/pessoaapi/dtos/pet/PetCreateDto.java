package br.com.dbc.vemser.pessoaapi.dtos.pet;

import br.com.dbc.vemser.pessoaapi.entity.TipoPet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetCreateDto {
    @NotNull
    private Integer idPessoa;
    @NotNull
    private String nome;
    @NotNull
    private TipoPet tipo;
}
