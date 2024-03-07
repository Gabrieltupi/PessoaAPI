package br.com.dbc.vemser.pessoaapi.controller;


import br.com.dbc.vemser.pessoaapi.config.PropertiesReader;
import br.com.dbc.vemser.pessoaapi.dtos.endereco.EnderecoCreateDto;
import br.com.dbc.vemser.pessoaapi.dtos.endereco.EnderecoDto;
import br.com.dbc.vemser.pessoaapi.service.EmailService;
import br.com.dbc.vemser.pessoaapi.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;
    private EmailService emailService;
    private PropertiesReader propertiesReader;


    @GetMapping //get localhost:8080/endereco
    public List<EnderecoDto> getAllEnderecos() throws Exception {
        return enderecoService.list();
    }




//    @GetMapping("/pessoa/{idPessoa}") //get localhost:8080/endereco/pessoa/idpessoa
//    public List<EnderecoDto> getEnderecosByPessoaId(@PathVariable Integer idPessoa)throws Exception {
//        return enderecoService.getEnderecosByPessoaId(idPessoa);
//    }

    @PostMapping //post localhost:8080/endereco
    public ResponseEntity<EnderecoDto> create(@Valid @RequestBody EnderecoCreateDto endereco) throws Exception {
        EnderecoDto enderecoCriado= enderecoService.create(endereco);

        return new ResponseEntity<>( enderecoCriado, HttpStatus.OK);
    }




    @PutMapping("/{idEndereco}") //put localhost:8080/endereco/idendereco
    public ResponseEntity<EnderecoDto> update(@PathVariable("idEndereco") Integer idEndereco,@Valid @RequestBody EnderecoCreateDto enderecoAtualizado) throws Exception {

        return new ResponseEntity<>(enderecoService.update(idEndereco, enderecoAtualizado), HttpStatus.OK);
    }


    @DeleteMapping("/{idEndereco}") //delete localhost:8080/endereco/idendereco
    public ResponseEntity<Void> delete(@PathVariable("idEndereco") Integer id) throws Exception {
        enderecoService.delete(id);
        emailService.sendEmail();

        return ResponseEntity.ok().build();
    }

/* referencias para realizar os codigos:

https://pt.stackoverflow.com/questions/310095/jackson-e-json-array
https://stackoverflow.com/questions/26549379/when-use-responseentityt-and-restcontroller-for-spring-restful-applications
 */
}