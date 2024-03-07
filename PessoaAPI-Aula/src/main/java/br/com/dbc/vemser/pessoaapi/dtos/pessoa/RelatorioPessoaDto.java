package br.com.dbc.vemser.pessoaapi.dtos.pessoa;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioPessoaDto{
    private String nome;
    private String email;
    private String numero;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
    private String nomePet;

}
