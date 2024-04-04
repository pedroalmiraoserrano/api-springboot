package com.gerenciarfuncionarios.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;

@JsonInclude(content = JsonInclude.Include.NON_EMPTY, value = JsonInclude.Include.NON_NULL)
public class ColaboradorDto {

    private Long id;

    private String nome;

    private String cpf;

    private String rg;

    private LocalDate dataNascimento;

    private Double salario;

    private Long gerenteId;

    public ColaboradorDto(Long id,
                          String nome,
                          String cpf,
                          String rg,
                          LocalDate dataNascimento,
                          Double salario,
                          Long gerenteId) {
      this.id = id;
      this.nome = nome;
      this.cpf = cpf;
      this.rg = rg;
      this.dataNascimento = dataNascimento;
      this.salario = salario;
      this.gerenteId = gerenteId;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

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
