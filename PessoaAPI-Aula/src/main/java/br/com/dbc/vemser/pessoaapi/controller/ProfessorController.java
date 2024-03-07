package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.config.PropertiesReader;
import br.com.dbc.vemser.pessoaapi.dtos.professor.ProfessorCreateDto;
import br.com.dbc.vemser.pessoaapi.dtos.professor.ProfessorDto;
import br.com.dbc.vemser.pessoaapi.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;
    private PropertiesReader propertiesReader;

    @GetMapping
    public List<ProfessorDto> getAllEnderecos() throws Exception{
        return professorService.list();
    }

    @PostMapping //post localhost:8080/endereco
    public ResponseEntity<ProfessorDto> create(@Valid @RequestBody ProfessorCreateDto prof) throws Exception {
        ProfessorDto professorCriado= professorService.create(prof);

        return new ResponseEntity<>( professorCriado, HttpStatus.OK);
    }


}
