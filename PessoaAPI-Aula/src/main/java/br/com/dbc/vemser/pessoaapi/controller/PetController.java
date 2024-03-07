package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.config.PropertiesReader;
import br.com.dbc.vemser.pessoaapi.dtos.pet.PetCreateDto;
import br.com.dbc.vemser.pessoaapi.dtos.pet.PetDto;
import br.com.dbc.vemser.pessoaapi.exeptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;
    private final PropertiesReader propertiesReader;



    @GetMapping
    public List<PetDto> list() throws RegraDeNegocioException{
        return petService.list();
    }

    @PostMapping
    public ResponseEntity<PetDto> create(@Valid @RequestBody PetCreateDto pet) throws Exception{
        PetDto entity= petService.create(pet);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PutMapping("/{idPet}") // PUT localhost:8080/pet/1
    public ResponseEntity<PetDto> update(@PathVariable("idPet") Integer id, @RequestBody @Valid PetCreateDto petAtualizar) throws Exception {

        return new ResponseEntity<>(petService.update(id, petAtualizar), HttpStatus.OK);

    }

    @DeleteMapping("/{idPet}")
    public ResponseEntity<Void> delete(@PathVariable("idPet") Integer id) throws Exception {
        petService.delete(id);

        return ok().build();
    }
}
