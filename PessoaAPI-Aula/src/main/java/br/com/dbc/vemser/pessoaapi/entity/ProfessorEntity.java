package br.com.dbc.vemser.pessoaapi.entity;

import br.com.dbc.vemser.pessoaapi.entity.pk.ProfessorPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name= "PROFESSOR")
public class ProfessorEntity {
    @EmbeddedId
    private ProfessorPK professorPK;

    @Column
    private String nome;

    @Column
    private Long salario;

}
