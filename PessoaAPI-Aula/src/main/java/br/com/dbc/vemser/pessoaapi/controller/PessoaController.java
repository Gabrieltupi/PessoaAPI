    package br.com.dbc.vemser.pessoaapi.controller;

    import br.com.dbc.vemser.pessoaapi.config.PropertiesReader;
    import br.com.dbc.vemser.pessoaapi.dtos.pessoa.*;
    import br.com.dbc.vemser.pessoaapi.entity.PessoaEntity;
    import br.com.dbc.vemser.pessoaapi.service.EmailService;
    import br.com.dbc.vemser.pessoaapi.service.PessoaService;
    import br.com.dbc.vemser.pessoaapi.exeptions.RegraDeNegocioException;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.format.annotation.DateTimeFormat;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.validation.annotation.Validated;
    import org.springframework.web.bind.annotation.*;

    import javax.validation.Valid;
    import java.time.LocalDate;
    import java.util.List;

    import static org.springframework.http.ResponseEntity.ok;

    @RestController
    @RequestMapping("/pessoa") // localhost:8080/pessoa
    @Validated
    @Slf4j
    public class PessoaController {

        private final PessoaService pessoaService;
        private EmailService emailService;
        private PropertiesReader propertiesReader;

        public PessoaController(PessoaService pessoaService, EmailService emailService, PropertiesReader propertiesReader) {
            this.pessoaService = pessoaService;
            this.emailService= emailService;
            this.propertiesReader= propertiesReader;
        }

        @GetMapping("/ambiente")
        public String retornarPropertie() {
            return propertiesReader.getAmbiente();
        }

        @GetMapping("/email")
        public String email() throws Exception{
            emailService.sendEmail();

            log.info("E-mail enviado!");
            return "E-mail enviado pela " +log  + "!";
        }




        @GetMapping // GET localhost:8080/pessoa
        public List<PessoaDto> list() throws RegraDeNegocioException {
            return pessoaService.list();
        }


        @GetMapping("/byname-contains") // GET localhost:8080/pessoa/byname?nome=Rafa
        public ResponseEntity<List<PessoaDto>> listByName(@RequestParam("nome") String nome) throws RegraDeNegocioException {
            return ResponseEntity.ok(pessoaService.findAllByNomeContains(nome));
        }

        @GetMapping("/bycpf-contains/{cpf}")
        public ResponseEntity<PessoaDto> getByCpf(@PathVariable @RequestParam("cpf") String cpf) throws RegraDeNegocioException{
            PessoaDto pessoaDto = pessoaService.findbyCpf(cpf);
            return ResponseEntity.ok(pessoaDto);
        }

        @GetMapping("/pessoas/por-data-nascimento")
        public ResponseEntity<List<PessoaDto>> getPessoasPorDataNascimento(
                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
            List<PessoaDto> pessoas = pessoaService.findByDataNascimentoBetween(dataInicial, dataFinal);
            return ResponseEntity.ok(pessoas);
        }

        @GetMapping("/com-enderecos")
        public ResponseEntity<List<PessoaEnderecoDto>> listWithAddress(@RequestParam(required = false) Integer idPessoa) {
            List<PessoaEnderecoDto> pessoasComEnderecos = pessoaService.listWithAddress(idPessoa);
            return ResponseEntity.ok(pessoasComEnderecos);
        }

        @GetMapping("/com-contato")
        public ResponseEntity<List<PessoaContatoDto>> listaComContatos (@RequestParam(required=false)Integer id){
            List<PessoaContatoDto> pessoasComContato= pessoaService.listarCCoontatos(id);
            return ResponseEntity.ok(pessoasComContato);
        }

        @GetMapping("/com-pet")
        public ResponseEntity<List<PessoaPetDto>> lsitaComPets(@RequestParam(required = false) Integer idPessoa) {
            List<PessoaPetDto> pessoasComPet = pessoaService.listarPets(idPessoa);
            return ResponseEntity.ok(pessoasComPet);
        }

        @GetMapping("/pessoa-completo/{idPessoa}")
        public ResponseEntity<List<PessoaEntity>> listaTotal (@RequestParam(required = false)Integer idPessoa){
            List<PessoaEntity> entityList=pessoaService.listaTudo(idPessoa);
            return ResponseEntity.ok(entityList);
        }

        @GetMapping("/relatorio/{idPessoa}")
        public ResponseEntity<List<RelatorioPessoaDto>> relatorioPersonalizado (@RequestParam(required = false)Integer idPessoa){
            List<RelatorioPessoaDto> entityList=pessoaService.relatorioPersonalizado(idPessoa);
            return ResponseEntity.ok(entityList);
        }


        @PostMapping // POST localhost:8080/pessoa
        public ResponseEntity<PessoaDto> create(@Valid @RequestBody PessoaCrateDto pessoa) throws Exception {
            log.debug("Criando pessoa");

            PessoaDto pessoaCriada = pessoaService.create(pessoa);
            log.debug("Pessoa criada!");

            return new ResponseEntity<>(pessoaCriada, HttpStatus.OK);
        }


        @PutMapping("/{idPessoa}") // PUT localhost:8080/pessoa/1000
        public ResponseEntity<PessoaDto> update(@PathVariable("idPessoa") Integer id, @RequestBody @Valid PessoaCrateDto pessoaAtualizar) throws Exception {

            return new ResponseEntity<>(pessoaService.update(id, pessoaAtualizar), HttpStatus.OK);

        }

        @DeleteMapping("/{idPessoa}")
        public ResponseEntity<Void> delete(@PathVariable("idPessoa") Integer id) throws Exception {
            pessoaService.delete(id);

            return ok().build();
        }

    }
