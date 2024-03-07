package br.com.dbc.vemser.pessoaapi.entity.pk;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class ProfessorPK implements Serializable {

    @Column(name="id_professor")
    private Integer idProfessor;

    @Column(name="id_universidade")
    private Integer idUniversidade;

}
