    package br.com.dbc.vemser.pessoaapi.controller;

    import br.com.dbc.vemser.pessoaapi.config.PropertiesReader;
    import br.com.dbc.vemser.pessoaapi.dtos.contato.ContatoDto;
    import br.com.dbc.vemser.pessoaapi.dtos.contato.ContatocreateDto;
    import br.com.dbc.vemser.pessoaapi.service.ContatoService;
    import br.com.dbc.vemser.pessoaapi.service.EmailService;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.validation.annotation.Validated;
    import org.springframework.web.bind.annotation.*;

    import javax.validation.Valid;
    import java.util.List;

    @RestController
    @RequestMapping("/contato")//localhost:8080/contato
    @Validated
    public class ContatoController{
    private final ContatoService contatoService;
        private EmailService emailService;
        private PropertiesReader propertiesReader;

        public ContatoController(ContatoService contatoService, EmailService emailService) {
            this.contatoService = contatoService;
            this.emailService=emailService;

        }

        @GetMapping //get localhost:8080/contato
            public List<ContatoDto>list() throws Exception{
            return contatoService.list();
        }

        @GetMapping("/{idPessoa}") // get localhost:8080/contato/idpessoa
        public ResponseEntity<List<ContatoDto>> getContatosByPessoaId(@PathVariable Integer idPessoa) throws Exception {
            List<ContatoDto> cttPessoa = contatoService.listByPessoaId(idPessoa);
            return ResponseEntity.ok(cttPessoa);

        }



        @PostMapping // POST localhost:8080/contato/idpessoa
        public ResponseEntity<ContatoDto> create(@Valid @RequestBody ContatocreateDto contato) throws Exception {
            ContatoDto contatoCriado= contatoService.create(contato);

            return new ResponseEntity<>(contatoCriado, HttpStatus.OK);
        }

        @PutMapping("/edit/{idContato}") //put localhost:8080/contato/idcontato
        public ResponseEntity<ContatoDto> update(@PathVariable("idContato") Integer id, @RequestBody @Valid ContatocreateDto contatoAtualizado) throws Exception {

            return new ResponseEntity<>(contatoService.update(id,contatoAtualizado), HttpStatus.OK);
        }





        @DeleteMapping("/{idContato}") //delete localhost:8080/contato/idcontato
        public ResponseEntity<Void> delete(@PathVariable("idContato") Integer id) throws Exception {
            contatoService.delete(id);


            return ResponseEntity.ok().build();
        }




    }
