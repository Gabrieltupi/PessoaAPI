package br.com.dbc.vemser.pessoaapi.dtos.pessoa;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaCrateDto {
    @Schema(description = "Nome da Pessoa", required = true, example = "Rafael Lazzari")
    @NotBlank(message = "Nome não pode ser nulo ou vazio.")
    private String nome;

    @Schema(description = "data de nascimento da pessoa",required=true, example="1999-12-15")
    @NotNull(message="Data de nascimento não pode ser nulo!")
    @Past(message = "Data de nascimento nao pode ser uma data que ainda nao existe")
    private LocalDate dataNascimento;

    @Schema(description = "cpf da pessoa",required=true, example = "00000000000")
    @CPF(message = "O CPF precisa ter exatamente 11 digitos e ser verdadeiro")
    private String cpf;

    @Schema(description = "seuEmail@dominio")
    @Email
    private String email;
}
