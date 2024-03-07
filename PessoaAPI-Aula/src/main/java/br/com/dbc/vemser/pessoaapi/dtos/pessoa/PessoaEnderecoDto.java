package br.com.dbc.vemser.pessoaapi.dtos.pessoa;

import br.com.dbc.vemser.pessoaapi.entity.EnderecoEntity;
import lombok.Data;

import java.util.Set;

@Data
public class PessoaEnderecoDto extends PessoaDto {
    private Set<EnderecoEntity> enderecos;
}
