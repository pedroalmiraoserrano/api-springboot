package com.gerenciarfuncionarios.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "gerente")
@Inheritance(strategy = InheritanceType.JOINED)
public class Gerente extends Funcionario {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gerente")
    @Fetch(FetchMode.SELECT)
    private List<Funcionario> funcionarios = new LinkedList<>();

    public Gerente() {
    }

    public Gerente(String nome,
                   String cpf,
                   String rg,
                   LocalDate dataNascimento,
                   Double salario,
                   Gerente gerente) {
        super(nome, cpf, rg, dataNascimento, salario, gerente);
    }

    public static Gerente criar(String nome,
                                String cpf,
                                String rg,
                                LocalDate dataNascimento,
                                Double salario,
                                Gerente gerente) {
        return new Gerente(nome, cpf, rg, dataNascimento, salario, gerente);
    }

    public List<Funcionario> getFuncionarios() {
        return this.funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionario.setGerente(this);
        this.funcionarios.add(funcionario);
    }

    public void removerFuncionario(Funcionario funcionario) {
        funcionario.setGerente(this);
        funcionarios.remove(funcionario);
    }

}
