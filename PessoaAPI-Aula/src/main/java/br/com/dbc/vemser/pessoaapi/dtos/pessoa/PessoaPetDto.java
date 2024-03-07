package br.com.dbc.vemser.pessoaapi.dtos.pessoa;

import br.com.dbc.vemser.pessoaapi.entity.PetEntity;
import lombok.Data;

import java.util.Set;
@Data
public class PessoaPetDto extends PessoaDto{
    private Set<PetEntity> pets;
}
