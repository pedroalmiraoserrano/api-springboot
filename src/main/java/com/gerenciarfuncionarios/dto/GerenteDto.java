package com.gerenciarfuncionarios.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import java.util.List;

@JsonInclude(content = JsonInclude.Include.NON_EMPTY, value = JsonInclude.Include.NON_NULL)
public class GerenteDto extends ColaboradorDto {

    private List<FuncionarioDto> funcionarios;

    public GerenteDto(Long id,
                      String nome,
                      String cpf,
                      String rg,
                      LocalDate dataNascimento,
                      Double salario,
                      Long gerenteId,
                      List<FuncionarioDto> funcionarios) {
      super(id, nome, cpf, rg, dataNascimento, salario, gerenteId);
      this.funcionarios = funcionarios;
    }

    public List<FuncionarioDto> getFuncionarios() {
      return funcionarios;
    }

    public void setFuncionarios(List<FuncionarioDto> funcionarios) {
      this.funcionarios = funcionarios;
    }

}
