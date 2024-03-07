package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dtos.contato.ContatoDto;
import br.com.dbc.vemser.pessoaapi.dtos.contato.ContatocreateDto;
import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.entity.ContatoEntity;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEntity;
import br.com.dbc.vemser.pessoaapi.exeptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.ContatoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final PessoaService pessoaService;
    private final ObjectMapper objectMapper;
    private final String NOT_FOUND_MESSAGE = "ID não encontrado";

        public ContatoDto create(ContatocreateDto contato) throws RegraDeNegocioException {
            PessoaEntity pessoaEntity= pessoaService.findById(contato.getIdPessoa());
        ContatoEntity contatoEntity = convertDto(contato);
            contatoEntity.setPessoaEntity(pessoaEntity);
        return retornarDto(contatoRepository.save(contatoEntity));
        }


    public ContatoDto update(Integer id, ContatocreateDto contatoDto) throws RegraDeNegocioException {
        ContatoEntity contatoEntityRecuperado = findById(id);
        PessoaEntity pessoaEntity= pessoaService.findById(contatoDto.getIdPessoa());

        contatoEntityRecuperado.setPessoaEntity(pessoaEntity);
        contatoEntityRecuperado.setTipoContato(contatoDto.getTipoContato());
        contatoEntityRecuperado.setNumero(contatoDto.getNumero());
        contatoEntityRecuperado.setDescricao(contatoDto.getDescricao());

        return retornarDto(contatoRepository.save(contatoEntityRecuperado));
    }

    public ContatoEntity convertDto(ContatocreateDto dto) {
        return objectMapper.convertValue(dto, ContatoEntity.class);
    }
    public ContatoDto retornarDto(ContatoEntity entity) {
        return objectMapper.convertValue(entity, ContatoDto.class);
    }

    public List<ContatoDto> list() {
        return contatoRepository.findAll().stream()
                .map(this::retornarDto)
                .collect(Collectors.toList());
    }


    public ContatoDto convertToDto(Contato entity) {
        return objectMapper.convertValue(entity, ContatoDto.class);
    }

    public ContatoEntity findById(Integer id) throws RegraDeNegocioException {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE));
    }

    public ContatoDto getById(Integer id) throws RegraDeNegocioException {
        ContatoEntity entity = findById(id);
        ContatoDto dto=retornarDto(entity);
        return dto;
    }

    public void delete(Integer id) {
        contatoRepository.deleteById(id);
    }

    public List<ContatoDto> listByPessoaId(Integer idPessoa) throws RegraDeNegocioException {
        PessoaEntity pessoaEntity = pessoaService.findById(idPessoa);
        List<ContatoEntity> contatosDaPessoa = contatoRepository.findAllByPessoaEntity(pessoaEntity);
        return contatosDaPessoa.stream()
                .map(this::retornarDto)
                .collect(Collectors.toList());
    }

}
