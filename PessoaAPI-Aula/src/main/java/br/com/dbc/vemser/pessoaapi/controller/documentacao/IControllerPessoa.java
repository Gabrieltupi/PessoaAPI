package br.com.dbc.vemser.pessoaapi.controller.documentacao;

import br.com.dbc.vemser.pessoaapi.dtos.pessoa.PessoaCrateDto;
import br.com.dbc.vemser.pessoaapi.dtos.pessoa.PessoaDto;
import br.com.dbc.vemser.pessoaapi.exeptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IControllerPessoa {

    @Operation(summary = "Listar pessoas", description = "Lista todas as pessoas do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de pessoas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping // GET localhost:8080/pessoa
    public List<PessoaDto> list() throws RegraDeNegocioException;

    @GetMapping("/byname") // GET localhost:8080/pessoa/byname?nome=Rafa&var2=xxx
    public List<PessoaDto> listByName(@RequestParam("nome") String nome) throws RegraDeNegocioException;

    @PostMapping // POST localhost:8080/pessoa
    public ResponseEntity<PessoaDto> create(@Valid @RequestBody PessoaCrateDto pessoa) throws RegraDeNegocioException;

    @PutMapping("/{idPessoa}") // PUT localhost:8080/pessoa/1000
    public ResponseEntity<PessoaDto> update(@PathVariable("idPessoa") Integer id, @RequestBody @Valid PessoaCrateDto pessoaAtualizar) throws RegraDeNegocioException;

    @DeleteMapping("/{idPessoa}")
    public ResponseEntity<Void> delete(@PathVariable("idPessoa") Integer id) throws RegraDeNegocioException;

}
