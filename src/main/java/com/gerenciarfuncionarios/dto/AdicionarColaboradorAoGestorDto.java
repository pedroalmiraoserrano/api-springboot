package com.gerenciarfuncionarios.dto;

import java.util.List;

public class AdicionarColaboradorAoGestorDto {

    private List<Long> funcionarioIds;

    public List<Long> getFuncionarioIds() {
        return funcionarioIds;
    }

    public void setFuncionarioIds(List<Long> funcionarioIds) {
        this.funcionarioIds = funcionarioIds;
    }
}
