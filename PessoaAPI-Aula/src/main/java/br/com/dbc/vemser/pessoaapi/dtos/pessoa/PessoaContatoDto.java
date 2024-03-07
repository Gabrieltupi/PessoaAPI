package br.com.dbc.vemser.pessoaapi.dtos.pessoa;

import br.com.dbc.vemser.pessoaapi.entity.Contato;
import lombok.Data;

import java.util.Set;

@Data
public class PessoaContatoDto extends PessoaDto{
    private Set<Contato> contatos;

}
