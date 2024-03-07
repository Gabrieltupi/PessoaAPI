package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dtos.pet.PetCreateDto;
import br.com.dbc.vemser.pessoaapi.dtos.pet.PetDto;
import br.com.dbc.vemser.pessoaapi.entity.PetEntity;
import br.com.dbc.vemser.pessoaapi.exeptions.EntidadeNaoEncontradaException;
import br.com.dbc.vemser.pessoaapi.exeptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.PetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    private final ObjectMapper objectMapper;
    private final String NOT_FOUND_MESSAGE="ID nao encontrado";

    public PetDto create (PetCreateDto pet){
        PetEntity entity=converterDto(pet);
        return retornarDto(petRepository.save(entity));
    }

    public List<PetDto> list() {
        return petRepository.findAll().stream()
                .map(this::retornarDto)
                .collect(Collectors.toList());
    }

    public PetEntity findById(Integer id) throws RegraDeNegocioException {
        PetEntity entity = petRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("pet não econtrada"));
        return entity;
    }

    public PetDto getById(Integer id) throws RegraDeNegocioException {
        PetEntity entity = findById(id);
        PetDto dto = retornarDto(entity);
        return dto;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
            PetEntity entityRecuperada = findById(id);
            petRepository.delete(entityRecuperada);
    }

    public PetDto update(Integer id, PetCreateDto pet) throws RegraDeNegocioException, EntidadeNaoEncontradaException {
        PetEntity entityRecuperada = findById(id);

        entityRecuperada.setNome(pet.getNome());
        entityRecuperada.setTipo(pet.getTipo());

        return retornarDto(petRepository.save(entityRecuperada));
    }

    public PetEntity converterDto(PetCreateDto dto) {
        return objectMapper.convertValue(dto, PetEntity.class);
    }
    public PetDto retornarDto(PetEntity entity) {
        return objectMapper.convertValue(entity, PetDto.class);
    }

}
