package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.dtos.pessoa.RelatorioPessoaDto;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity,Integer> {

    List<PessoaEntity> findByDataNascimentoBetween(LocalDate dataInicial, LocalDate dataFinal);

    public List<PessoaEntity> findAllByNomeContainsIgnoreCase(String nome);

    public List<PessoaEntity> findByCpf(String cpf);


    @Query("SELECT p FROM PESSOA p " +
            "LEFT JOIN FETCH p.enderecos " +
            "LEFT JOIN FETCH p.contatos " +
            "LEFT JOIN FETCH p.pets " +
            "WHERE p.idPessoa = :idPessoa")
    Optional<PessoaEntity> pessoaEnderecoContatoPet(@Param("idPessoa") Integer idPessoa);



    @Query(value = "SELECT " +
            "   p.NOME AS nome, " +
            "   p.EMAIL AS email, " +
            "   c.NUMERO AS numero, " +
            "   e.CEP AS cep, " +
            "   e.CIDADE AS cidade, " +
            "   e.ESTADO AS estado, " +
            "   e.PAIS AS pais, " +
            "   pet.NOME AS nomePet " +
            "FROM " +
            "   PESSOA p " +
            "JOIN " +
            "   CONTATO c ON p.ID_PESSOA = c.ID_PESSOA " +
            "JOIN " +
            "   PESSOA_X_PESSOA_ENDERECO pxpe ON p.ID_PESSOA = pxpe.ID_PESSOA " +
            "JOIN " +
            "   ENDERECO_PESSOA e ON pxpe.ID_ENDERECO = e.ID_ENDERECO " +
            "LEFT JOIN " +
            "   PET pet ON p.ID_PET = pet.ID_PET " +
            "WHERE "+
            "   p.ID_PESSOA = :idPessoa", nativeQuery = true)
    List<Object[]> relatorioId(@Param("idPessoa") Integer idPessoa);


    @Query(value = "SELECT " +
            "   p.NOME AS nome, " +
            "   p.EMAIL AS email, " +
            "   c.NUMERO AS numero, " +
            "   e.CEP AS cep, " +
            "   e.CIDADE AS cidade, " +
            "   e.ESTADO AS estado, " +
            "   e.PAIS AS pais, " +
            "   pet.NOME AS nomePet " +
            "FROM " +
            "   PESSOA p " +
            "JOIN " +
            "   CONTATO c ON p.ID_PESSOA = c.ID_PESSOA " +
            "JOIN " +
            "   PESSOA_X_PESSOA_ENDERECO pxpe ON p.ID_PESSOA = pxpe.ID_PESSOA " +
            "JOIN " +
            "   ENDERECO_PESSOA e ON pxpe.ID_ENDERECO = e.ID_ENDERECO " +
            "LEFT JOIN " +
            "   PET pet ON p.ID_PET = pet.ID_PET", nativeQuery = true)
    List<Object[]> relatorioGeral();

}


//https://stackoverflow.com/questions/39784344/check-date-between-two-other-dates-spring-data-jpa
//https://stackoverflow.com/questions/22782706/passing-object-to-sql-query