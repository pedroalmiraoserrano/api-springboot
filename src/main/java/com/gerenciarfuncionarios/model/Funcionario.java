package com.gerenciarfuncionarios.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "funcionario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Funcionario extends Colaborador {

    @JoinColumn(name = "id_gerente")
    @ManyToOne(fetch = FetchType.EAGER)
    private Gerente gerente;

    public Funcionario() {
    }

    public Funcionario(String nome,
                       String cpf,
                       String rg,
                       LocalDate dataNascimento,
                       Double salario,
                       Gerente gerente) {
        super(nome, cpf, rg, dataNascimento, salario);
        this.gerente = gerente;
    }

    public static Funcionario criar(String nome,
                                    String cpf,
                                    String rg,
                                    LocalDate dataNascimento,
                                    Double salario,
                                    Gerente gerente) {
        return new Funcionario(nome,
            cpf,
            rg,
            dataNascimento,
            salario, gerente);
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }
}
