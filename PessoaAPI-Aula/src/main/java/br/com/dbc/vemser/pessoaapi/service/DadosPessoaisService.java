package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.client.IDadosPessoaisClient;
import br.com.dbc.vemser.pessoaapi.dtos.DadosPessoaisDto;
import br.com.dbc.vemser.pessoaapi.exeptions.RegraDeNegocioException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DadosPessoaisService {
    private final IDadosPessoaisClient client;
    private final ObjectMapper objectMapper;
    public DadosPessoaisDto create (DadosPessoaisDto dados) throws RegraDeNegocioException {
        log.debug("criando dados");
        return client.post(dados);
    }
    public List<DadosPessoaisDto> list ()throws RegraDeNegocioException{
        return client.getAll();
    }

    public DadosPessoaisDto put (String cpf, DadosPessoaisDto dados) throws RegraDeNegocioException {
        return client.put(cpf,dados);
    }

    public void delete(String cpf) throws RegraDeNegocioException{
        client.delete(cpf);
    }

    public DadosPessoaisDto get(String cpf) throws RegraDeNegocioException{
        return client.get(cpf);
    }
}
