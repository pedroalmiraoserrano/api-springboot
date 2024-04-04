package com.gerenciarfuncionarios.dto;

import java.util.List;

public class CriarGerenteDto extends CriarColaboradorDto {

    private List<Long> funcionariosIds;

    public List<Long> getFuncionariosIds() {
        return funcionariosIds;
    }

    public void setFuncionariosIds(List<Long> funcionariosIds) {
        this.funcionariosIds = funcionariosIds;
    }

}
