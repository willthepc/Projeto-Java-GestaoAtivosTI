package com.projeto.gestaoAtivos.repositories;

import com.projeto.gestaoAtivos.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
