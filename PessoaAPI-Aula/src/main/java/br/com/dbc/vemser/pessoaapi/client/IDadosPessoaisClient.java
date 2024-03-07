package br.com.dbc.vemser.pessoaapi.client;

import br.com.dbc.vemser.pessoaapi.dtos.DadosPessoaisDto;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(value="dados-pessoais-api", url="http://vemser-dbc.dbccompany.com.br:39000/vemser/dados-pessoais-api")
@Headers("Content-Type: application/json")
public interface IDadosPessoaisClient {

    @RequestLine("GET /dados-pessoais")
    List<DadosPessoaisDto> getAll();

    @RequestLine("POST /dados-pessoais")
    DadosPessoaisDto post(DadosPessoaisDto dadosPessoaisDTO);

    @RequestLine("PUT /dados-pessoais/{cpf}")
    DadosPessoaisDto put(@Param("cpf") String cpf, DadosPessoaisDto dadosPessoaisDTO);

    @RequestLine("DELETE /dados-pessoais/{cpf}")
    void delete(@Param("cpf") String cpf);

    @RequestLine("GET /dados-pessoais/{cpf}")
    DadosPessoaisDto get(@Param("cpf") String cpf);
}