package br.com.dbc.vemser.pessoaapi.service;


import br.com.dbc.vemser.pessoaapi.dtos.professor.ProfessorCreateDto;
import br.com.dbc.vemser.pessoaapi.dtos.professor.ProfessorDto;
import br.com.dbc.vemser.pessoaapi.entity.ProfessorEntity;
import br.com.dbc.vemser.pessoaapi.repository.ProfessorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ObjectMapper objectMapper;
    private final String NOT_FOUND_MESSAGE = "ID não encontrado";

    public ProfessorDto create (ProfessorCreateDto prof){
        ProfessorEntity professorEntity=convertDto(prof);
        retornarDto(professorRepository.save(professorEntity));
        return retornarDto(professorEntity);
    }


    public List<ProfessorDto> list(){
        return professorRepository.findAll().stream().map(this::retornarDto).collect(Collectors.toList());
    }


    public ProfessorEntity convertDto(ProfessorCreateDto dto) {
        return objectMapper.convertValue(dto, ProfessorEntity.class);
    }

    public ProfessorDto retornarDto(ProfessorEntity entity) {
        return objectMapper.convertValue(entity, ProfessorDto.class);
    }

//        public ProfessorDto getById (Integer id) throws RegraDeNegocioException{
//        ProfessorEntity entity= findById(id);
//        ProfessorDto dto = retornarDto(entity);
//        return dto;
//    }
//
//    public ProfessorEntity findById(Integer id) throws RegraDeNegocioException{
//        ProfessorEntity entity= professorRepository.findById(id).orElseThrow(()-> new RegraDeNegocioException("professor não encontrado"));
//        return entity;
//    }
//
//    public ProfessorDto update (Integer id, ProfessorCreateDto prof) throws RegraDeNegocioException{
//        ProfessorEntity professorEntityAtu= findById(id);
//
//        professorEntityAtu.setNome(prof.getNome());
//        professorEntityAtu.setSalario(prof.getSalario());
//
//        return retornarDto(professorRepository.save(professorEntityAtu));
//    }
//
//    public void delete(Integer id) {
//       professorRepository.deleteById(id);
//    }

}

