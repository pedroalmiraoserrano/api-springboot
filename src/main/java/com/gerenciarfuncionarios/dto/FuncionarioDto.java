package com.gerenciarfuncionarios.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;

@JsonInclude(content = JsonInclude.Include.NON_EMPTY, value = JsonInclude.Include.NON_NULL)
public class FuncionarioDto extends ColaboradorDto {

    public FuncionarioDto(Long id,
                          String nome,
                          String cpf,
                          String rg,
                          LocalDate dataNascimento,
                          Double salario,
                          Long gerenteId) {
      super(id, nome, cpf, rg, dataNascimento, salario, gerenteId);
    }

}
