package com.gerenciarfuncionarios.mapper;

import com.gerenciarfuncionarios.dto.FuncionarioDto;
import com.gerenciarfuncionarios.dto.GerenteDto;
import com.gerenciarfuncionarios.model.Funcionario;
import java.util.LinkedList;
import java.util.List;

public class FuncionarioMapper {

  public static FuncionarioDto entidadeParaDto(Funcionario funcionario) {

    final Long gerenteId = funcionario.getGerente() != null
        ? funcionario.getGerente().getId()
        : null;

    return new FuncionarioDto(
        funcionario.getId(),
        funcionario.getNome(),
        funcionario.getCpf(),
        funcionario.getRg(),
        funcionario.getDataNascimento(),
        funcionario.getSalario(),
        gerenteId
    );

  }

  public static List<FuncionarioDto> entidadeParaDto(List<Funcionario> funcionarios) {
    List<FuncionarioDto> funcionarioDtos = new LinkedList<>();
    funcionarios.forEach(funcionario -> funcionarioDtos.add(FuncionarioMapper.entidadeParaDto(funcionario)));
    return funcionarioDtos;
  }

}
