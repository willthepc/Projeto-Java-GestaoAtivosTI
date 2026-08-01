package com.projeto.gestaoAtivos.repositories;

import com.projeto.gestaoAtivos.entities.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

    boolean existsByFuncionarioIdAndType(Long funcionarioId, String type);

    boolean existsByFuncionarioIdAndTypeAndIdNot(Long funcionarioId, String type, Long id);
}
