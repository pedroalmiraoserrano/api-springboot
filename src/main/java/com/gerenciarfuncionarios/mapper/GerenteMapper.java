package com.gerenciarfuncionarios.mapper;

import com.gerenciarfuncionarios.dto.GerenteDto;
import com.gerenciarfuncionarios.model.Funcionario;
import com.gerenciarfuncionarios.model.Gerente;

import java.util.LinkedList;
import java.util.List;

public class GerenteMapper {

  public static GerenteDto entidadeParaDto(Gerente gerente) {

    final Long gerenteId = gerente.getGerente() != null
            ? gerente.getGerente().getId()
            : null;

    return new GerenteDto(
        gerente.getId(),
        gerente.getNome(),
        gerente.getCpf(),
        gerente.getRg(),
        gerente.getDataNascimento(),
        gerente.getSalario(),
        gerenteId,
        FuncionarioMapper.entidadeParaDto(gerente.getFuncionarios())
    );
  }

  public static List<GerenteDto> entidadeParaDto(final List<Gerente> gerentes) {
    final List<GerenteDto> gerenteDtos = new LinkedList<>();
    gerentes.forEach(gerente -> gerenteDtos.add(entidadeParaDto(gerente)));
    return gerenteDtos;
  }

}
