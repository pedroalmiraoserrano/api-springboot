package com.gerenciarfuncionarios.repository;

import com.gerenciarfuncionarios.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

    @Query( " select f " +
            " from Funcionario f " +
            " where f.cpf = :cpf ")
    Funcionario buscarPorCpf(@Param("cpf") String cpf);

}
