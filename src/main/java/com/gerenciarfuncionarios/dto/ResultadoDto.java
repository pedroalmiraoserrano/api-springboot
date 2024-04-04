package com.gerenciarfuncionarios.dto;

public class ResultadoDto {

    private String mensagem;

    public ResultadoDto(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
