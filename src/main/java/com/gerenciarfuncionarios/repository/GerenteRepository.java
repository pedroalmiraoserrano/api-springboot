package com.gerenciarfuncionarios.repository;

import com.gerenciarfuncionarios.model.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {
}
