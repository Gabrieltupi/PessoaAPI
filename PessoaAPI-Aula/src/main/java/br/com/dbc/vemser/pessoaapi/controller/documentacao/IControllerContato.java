package br.com.dbc.vemser.pessoaapi.controller.documentacao;

import br.com.dbc.vemser.pessoaapi.dtos.contato.ContatoDto;
import br.com.dbc.vemser.pessoaapi.dtos.contato.ContatocreateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IControllerContato {

    @Operation(summary = "Listar pessoas", description = "Lista todas as pessoas do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de pessoas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping //get localhost:8080/contato
    public List<ContatoDto> list() throws Exception;

    @GetMapping("/{idPessoa}") // get localhost:8080/contato/idpessoa
    public List<ContatoDto> getContatosByPessoaId(@PathVariable Integer idPessoa) throws Exception;

    @PostMapping // POST localhost:8080/contato
    public ResponseEntity<ContatoDto> create(@Valid @RequestBody ContatocreateDto contato) throws Exception;

    @PutMapping("/{idContato}") //put localhost:8080/contato/idcontato
    public ResponseEntity<ContatoDto> update(@PathVariable("idContato") Integer id, @RequestBody @Valid ContatocreateDto contatoAtualizado) throws Exception;

    @DeleteMapping("/{idContato}") //delete localhost:8080/contato/idcontato
    public ResponseEntity<Void> delete(@PathVariable("idContato") Integer id) throws Exception;
}
