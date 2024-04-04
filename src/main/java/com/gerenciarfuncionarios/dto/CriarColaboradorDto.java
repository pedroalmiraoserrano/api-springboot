package com.gerenciarfuncionarios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CriarColaboradorDto {

    @NotBlank(message = "Nome do funcionário não informado.")
    private String nome;

    @NotBlank(message = "CPF do funcionário não informado.")
    @Size(message = "CPF deve possuir no mínimo 14 caracteres, incluindo pontuação.", min = 14)
    private String cpf;

    @NotBlank(message = "RG do funcionário não informado.")
    private String rg;

    @NotNull(message = "Data de nascimento do funcionário não informada.")
    private LocalDate dataNascimento;

    @NotNull(message = "Salário do funcionário não informado.")
    @PositiveOrZero(message = "Salário do funcionário deve ser zero ou positivo.")
    private Double salario;

    private Long gerenteId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Long getGerenteId() {
        return gerenteId;
    }

    public void setGerenteId(Long gerenteId) {
        this.gerenteId = gerenteId;
    }
}
