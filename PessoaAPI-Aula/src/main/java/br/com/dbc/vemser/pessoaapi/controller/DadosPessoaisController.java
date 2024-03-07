package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dtos.DadosPessoaisDto;
import br.com.dbc.vemser.pessoaapi.exeptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.DadosPessoaisService;
import feign.Param;
import feign.RequestLine;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Validated
@RequestMapping("/dadosPessoais")
public class DadosPessoaisController {
    private final DadosPessoaisService dadosPessoaisService;

    public DadosPessoaisController(DadosPessoaisService dadosPessoaisService) {
        this.dadosPessoaisService = dadosPessoaisService;
    }


    @GetMapping("GET /dados-pessoais")
    public List<DadosPessoaisDto> getAll()throws RegraDeNegocioException {
        return dadosPessoaisService.list();
    }


    @PostMapping("POST /dados-pessoais")
    public DadosPessoaisDto post(DadosPessoaisDto dadosPessoaisDTO) throws RegraDeNegocioException {
        return dadosPessoaisService.create(dadosPessoaisDTO);
    }


    @PutMapping("PUT /dados-pessoais/{cpf}")
    public DadosPessoaisDto put(@Param("cpf") String cpf, DadosPessoaisDto dadosPessoaisDTO) throws RegraDeNegocioException{
        return dadosPessoaisService.put(cpf,dadosPessoaisDTO);
    }


    @DeleteMapping("DELETE /dados-pessoais/{cpf}")
    public void delete(@Param("cpf") String cpf)throws RegraDeNegocioException{
        dadosPessoaisService.delete(cpf);
    }

    @GetMapping("GET /dados-pessoais/{cpf}")
    public DadosPessoaisDto get(@Param("cpf") String cpf) throws RegraDeNegocioException {
        return dadosPessoaisService.get(cpf);
    }


}
