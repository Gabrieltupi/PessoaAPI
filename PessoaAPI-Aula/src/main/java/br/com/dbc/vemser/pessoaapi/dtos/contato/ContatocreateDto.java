package br.com.dbc.vemser.pessoaapi.dtos.contato;

import br.com.dbc.vemser.pessoaapi.entity.TipoContato;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContatocreateDto {
    @NotNull (message = "Id do Contato deve estar acompanhado do Id do usuario")
    private Integer idPessoa;

    @NotNull(message = "tipo de contato deve ser informado")
    @Schema(description = "tipo de contato", required = true, example = "residencial")
    private TipoContato tipoContato;

    @NotNull(message = "numero de celular vazio")
    @Size(max=13, message = "Os numeros validos normalmente sao até 13 caracteres")
    @Schema(description = "numero de contato", required = true, example = "51992521130")
    private String numero;

    @NotBlank (message = "A descrição de contato deve ser informada")
    @Schema(description = "descrição", example = "pessoal")
    private String descricao;
}
