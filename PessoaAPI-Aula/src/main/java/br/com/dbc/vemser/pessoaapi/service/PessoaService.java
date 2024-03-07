package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dtos.pessoa.*;
import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEntity;
import br.com.dbc.vemser.pessoaapi.exeptions.EntidadeNaoEncontradaException;
import br.com.dbc.vemser.pessoaapi.exeptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;
    private final String NOT_FOUND_MESSAGE="ID nao encontrado";

    public PessoaDto create(PessoaCrateDto pessoa){
        PessoaEntity pessoaEntity= converterDto(pessoa);
        return retornarDto(pessoaRepository.save(pessoaEntity));

    }
    public List<PessoaDto> list() {
        return pessoaRepository.findAll().stream()
                .map(this::retornarDto)
                .collect(Collectors.toList());
    }
    public PessoaEntity converterDto(PessoaCrateDto dto) {
        return objectMapper.convertValue(dto, PessoaEntity.class);
    }
    public PessoaDto retornarDto(PessoaEntity entity) {
        return objectMapper.convertValue(entity, PessoaDto.class);
    }

    public PessoaEntity findById(Integer id) throws RegraDeNegocioException {
        PessoaEntity entity = pessoaRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("pessoa não econtrada"));
        return entity;
    }

    public PessoaDto getById(Integer id) throws RegraDeNegocioException {
        PessoaEntity entity = findById(id);
        PessoaDto dto = retornarDto(entity);
        return dto;
    }

    public PessoaDto update(Integer id, PessoaCrateDto pessoaDto) throws RegraDeNegocioException, EntidadeNaoEncontradaException {
        PessoaEntity pessoaEntityRecuperada = returnPersonById(id);

        pessoaEntityRecuperada.setCpf(pessoaDto.getCpf());
        pessoaEntityRecuperada.setEmail(pessoaDto.getEmail());
        pessoaEntityRecuperada.setDataNascimento(pessoaDto.getDataNascimento());
        pessoaEntityRecuperada.setNome(pessoaDto.getNome());

        return retornarDto(pessoaRepository.save(pessoaEntityRecuperada));
    }

    public void delete(Integer id) {
        try {
            PessoaEntity pessoaEntityRecuperada = returnPersonById(id);
            pessoaRepository.delete(pessoaEntityRecuperada);
        } catch (EntidadeNaoEncontradaException ex){
            ex.printStackTrace();
        }
    }

    public PessoaEntity returnPersonById(Integer id) throws EntidadeNaoEncontradaException {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(NOT_FOUND_MESSAGE));
    }

    public List<PessoaDto> findAllByNomeContains(String nome) {
        return pessoaRepository.findAllByNomeContainsIgnoreCase(nome).stream()
                .map(this::retornarDto)
                .collect(Collectors.toList());
    }

    public PessoaDto findbyCpf (String cpf){
        return (PessoaDto) pessoaRepository.findByCpf(cpf).stream().map(this::retornarDto).findFirst().orElseThrow(null);
    }

    public List<PessoaDto> findByDataNascimentoBetween(LocalDate dataInicial, LocalDate dataFinal) {
        List<PessoaEntity> pessoas = pessoaRepository.findByDataNascimentoBetween(dataInicial, dataFinal);
        return pessoas.stream()
                .map(this::retornarDto)
                .collect(Collectors.toList());
    }



    public List<PessoaEnderecoDto> listWithAddress(Integer idPessoa) {
        List<PessoaEntity> pessoas;
        if (idPessoa != null) {
            Optional<PessoaEntity> pessoaOptional = pessoaRepository.findById(idPessoa);
            return pessoaOptional.map(pessoaEntity -> {
                PessoaEnderecoDto pessoaEnderecoDto = objectMapper.convertValue(pessoaEntity, PessoaEnderecoDto.class);
                pessoaEnderecoDto.setEnderecos(pessoaEntity.getEnderecos());
                return List.of(pessoaEnderecoDto);
            }).orElse(List.of());
        } else {
            pessoas = pessoaRepository.findAll();
        }

        return pessoas.stream()
                .map(pessoaEntity -> objectMapper.convertValue(pessoaEntity, PessoaEnderecoDto.class))
                .collect(Collectors.toList());
    }


    public List<PessoaContatoDto> listarCCoontatos(Integer idPessoa) {
        List<PessoaEntity> pessoas;
        if (idPessoa != null) {
            Optional<PessoaEntity> pessoaOptional = pessoaRepository.findById(idPessoa);
            return pessoaOptional.map(pessoaEntity -> {
                PessoaContatoDto pessoaContatoDto = objectMapper.convertValue(pessoaEntity, PessoaContatoDto.class);

                Set<Contato> contatos = pessoaEntity.getContatos().stream()
                        .map(contatoEntity -> objectMapper.convertValue(contatoEntity, Contato.class))
                        .collect(Collectors.toSet());

                pessoaContatoDto.setContatos(contatos);

                return List.of(pessoaContatoDto);
            }).orElse(List.of());
        } else {
            pessoas = pessoaRepository.findAll();
        }

        return pessoas.stream()
                .map(pessoaEntity -> {
                    PessoaContatoDto pessoaContatoDto = objectMapper.convertValue(pessoaEntity, PessoaContatoDto.class);

                    Set<Contato> contatos = pessoaEntity.getContatos().stream()
                            .map(contatoEntity -> objectMapper.convertValue(contatoEntity, Contato.class))
                            .collect(Collectors.toSet());

                    pessoaContatoDto.setContatos(contatos);

                    return pessoaContatoDto;
                })
                .collect(Collectors.toList());
    }


    public List<PessoaPetDto> listarPets(Integer idPessoa) {
        List<PessoaEntity> pessoas;
        if (idPessoa != null) {
            Optional<PessoaEntity> pessoaOptional = pessoaRepository.findById(idPessoa);
            return pessoaOptional.map(pessoaEntity -> {
                PessoaPetDto pessoaPetDto = objectMapper.convertValue(pessoaEntity, PessoaPetDto.class);
                pessoaPetDto.setPets(pessoaEntity.getPets());
                return List.of(pessoaPetDto);
            }).orElse(List.of());
        } else {
            pessoas = pessoaRepository.findAll();
        }

        return pessoas.stream()
                .map(pessoaEntity -> {
                    PessoaPetDto pessoaPetDto = objectMapper.convertValue(pessoaEntity, PessoaPetDto.class);
                    pessoaPetDto.setPets(pessoaEntity.getPets());
                    return pessoaPetDto;
                })
                .collect(Collectors.toList());
    }

    public List<PessoaEntity> listaTudo(Integer idPessoa) {
        if (idPessoa != null) {
            return pessoaRepository.pessoaEnderecoContatoPet(idPessoa)
                    .map(Collections::singletonList)
                    .orElse(Collections.emptyList());
        } else {
            return pessoaRepository.findAll();
        }
    }

    public List<RelatorioPessoaDto> relatorioPersonalizado(Integer idPessoa) {
        List<Object[]> results;
        if (idPessoa != null) {
            results = pessoaRepository.relatorioId(idPessoa);
        } else {
            results = pessoaRepository.relatorioGeral();
        }
        return results.stream().map(this::mapToRelatorioPessoaDto).collect(Collectors.toList());
    }

    private RelatorioPessoaDto mapToRelatorioPessoaDto(Object[] result) {
        RelatorioPessoaDto relatorio = new RelatorioPessoaDto();
        relatorio.setNome((String) result[0]);
        relatorio.setEmail((String) result[1]);
        relatorio.setNumero((String) result[2]);
        relatorio.setCep((String) result[3]);
        relatorio.setCidade((String) result[4]);
        relatorio.setEstado((String) result[5]);
        relatorio.setPais((String) result[6]);
        relatorio.setNomePet((String) result[7]);
        return relatorio;
    }
    
}
//https://www.geeksforgeeks.org/collections-singletonlist-method-in-java-with-examples/