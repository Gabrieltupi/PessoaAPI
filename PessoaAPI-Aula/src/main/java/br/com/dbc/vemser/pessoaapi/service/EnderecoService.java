package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dtos.endereco.EnderecoCreateDto;
import br.com.dbc.vemser.pessoaapi.dtos.endereco.EnderecoDto;
import br.com.dbc.vemser.pessoaapi.entity.EnderecoEntity;
import br.com.dbc.vemser.pessoaapi.exeptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ObjectMapper objectMapper;
    private final String NOT_FOUND_MESSAGE = "ID não encontrado";

    public EnderecoDto create(EnderecoCreateDto endereco) {
        EnderecoEntity enderecoEntity = convertDto(endereco);
        return retornarDto(enderecoRepository.save(enderecoEntity));
    }

    public EnderecoEntity convertDto(EnderecoCreateDto dto) {
        return objectMapper.convertValue(dto, EnderecoEntity.class);
    }

    public EnderecoDto retornarDto(EnderecoEntity entity) {
        return objectMapper.convertValue(entity, EnderecoDto.class);
    }

    public List<EnderecoDto> list() {
        return enderecoRepository.findAll().stream()
                .map(this::retornarDto)
                .collect(Collectors.toList());
    }

    public EnderecoEntity findById(Integer id) throws RegraDeNegocioException {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE));
    }

    public EnderecoDto getById(Integer id) throws RegraDeNegocioException {
        EnderecoEntity entity = findById(id);
        return retornarDto(entity);
    }

    public EnderecoDto update(Integer id, EnderecoCreateDto enderecoDto) throws RegraDeNegocioException {
        EnderecoEntity enderecoEntityRecuperado = findById(id);

//        enderecoEntityRecuperado.setIdPessoa(enderecoDto.getIdPessoa());
        enderecoEntityRecuperado.setTipo(enderecoDto.getTipo());
        enderecoEntityRecuperado.setLogradouro(enderecoDto.getLogradouro());
        enderecoEntityRecuperado.setNumero(enderecoDto.getNumero());
        enderecoEntityRecuperado.setComplemento(enderecoDto.getComplemento());
        enderecoEntityRecuperado.setCep(enderecoDto.getCep());
        enderecoEntityRecuperado.setCidade(enderecoDto.getCidade());
        enderecoEntityRecuperado.setEstado(enderecoDto.getEstado());
        enderecoEntityRecuperado.setPais(enderecoDto.getPais());

        return retornarDto(enderecoRepository.save(enderecoEntityRecuperado));
    }

    public void delete(Integer id) {
        enderecoRepository.deleteById(id);
    }

}