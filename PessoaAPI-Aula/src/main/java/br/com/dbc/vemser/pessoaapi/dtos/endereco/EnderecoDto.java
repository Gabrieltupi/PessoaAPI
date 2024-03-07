package br.com.dbc.vemser.pessoaapi.dtos.endereco;

import br.com.dbc.vemser.pessoaapi.entity.TipoEndereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnderecoDto extends EnderecoCreateDto {
    private Integer idEndereco;

    @NotNull (message = "Id do Contato deve estar acompanhado do Id do usuario")
    private Integer idPessoa;

    @NotNull (message = "tipo de endereço deve ser informado")
    @Schema(description = "tipo de endereco", example = "comercial")

    private TipoEndereco tipo;

    @NotBlank(message = "Logradouro deve ser informado")
    @Schema(description = "rua/av", required = true, example = "AV.Juventino Machado")
    private String logradouro;

    @NotBlank (message = "numero da residencia deve ser informado")
    @Schema(description = "numero da localidade", required = true, example = "665")
    private String numero;

    private String complemento;

    @NotNull (message = "CEP nao pode ser nulo")
    @Schema(description = "cep do lugar", required = true, example = "93230600")
    private String cep;

    @Schema(description = "cidade", required = true, example = "Sapucaia")
    private String cidade;

    @Schema(description = "estado", required = true, example = "RS")
    private String estado;

    @Schema(description = "pais", required = true, example = "brasil")
    private String pais;

}
