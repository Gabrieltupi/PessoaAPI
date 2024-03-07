package br.com.dbc.vemser.pessoaapi.controller.documentacao;

import br.com.dbc.vemser.pessoaapi.dtos.endereco.EnderecoCreateDto;
import br.com.dbc.vemser.pessoaapi.dtos.endereco.EnderecoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IControllerEndereco {
    @Operation(summary = "Listar pessoas", description = "Lista todas as pessoas do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de pessoas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping //get localhost:8080/endereco
    public List<EnderecoDto> getAllEnderecos() throws Exception;

    @GetMapping("/pessoa/{idPessoa}") //get localhost:8080/endereco/pessoa/idpessoa
    public List<EnderecoDto> getEnderecosByPessoaId(@PathVariable Integer idPessoa)throws Exception;

    @PostMapping //post localhost:8080/endereco
    public ResponseEntity<EnderecoDto> create(@Valid @RequestBody EnderecoCreateDto endereco) throws Exception;

    @PutMapping("/{idEndereco}") //put localhost:8080/endereco/idendereco
    public ResponseEntity<EnderecoDto> update(@PathVariable("idEndereco") Integer idEndereco,@Valid @RequestBody EnderecoCreateDto enderecoAtualizado) throws Exception;

    @DeleteMapping("/{idEndereco}") //delete localhost:8080/endereco/idendereco
    public ResponseEntity<Void> delete(@PathVariable("idEndereco") Integer id) throws Exception;


}
