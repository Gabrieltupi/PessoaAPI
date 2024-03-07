package br.com.dbc.vemser.pessoaapi.dtos.pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PessoaDto extends PessoaCrateDto{
    private Integer idPessoa;

}
