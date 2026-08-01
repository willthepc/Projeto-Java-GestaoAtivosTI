package com.projeto.gestaoAtivos.services;

import com.projeto.gestaoAtivos.dto.EquipamentoDto;
import com.projeto.gestaoAtivos.dto.FuncionarioDto;
import com.projeto.gestaoAtivos.entities.Equipamento;
import com.projeto.gestaoAtivos.entities.Funcionario;
import com.projeto.gestaoAtivos.exception.NotFoundException;
import com.projeto.gestaoAtivos.handler.GlobalExceptionHandler;
import com.projeto.gestaoAtivos.repositories.EquipamentoRepository;
import com.projeto.gestaoAtivos.repositories.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository repository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Equipamento> findAll() {
        return repository.findAll();
    }

    public Equipamento findById(Long id) {
        Optional<Equipamento> obj = Optional.of(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Equipamento não encontrado!")));
        return obj.get();
    }

    public Equipamento insert(EquipamentoDto obj) {
        Funcionario funcionario = funcionarioRepository.findById(obj.getFuncionarioId())
                .orElseThrow(() -> new NotFoundException("Funcionário não encontrado!"));

        Equipamento equipamento = Equipamento.builder()
                .model(obj.getModel())
                .type(obj.getType())
                .funcionario(funcionario)
                .build();

        boolean jaPossuiEsseTipo = repository.existsByFuncionarioIdAndType(funcionario.getId(), obj.getType());

        if (jaPossuiEsseTipo) {
            throw new IllegalStateException("O funcionário já tem esse tipo de equipamento.");
        }


        return repository.save(equipamento);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Equipamento não encontrado no banco de dados.");
        }

        try {

            repository.deleteById(id);

        } catch (DataIntegrityViolationException e) {

            throw new IllegalStateException("Não foi possível deletar: Há funcionário ligado a esse equipamento.");

        }
    }

    public Equipamento update(Long id, EquipamentoDto equipamentoDto) {
        try {

            Equipamento equipamento = repository.getReferenceById(id);

            boolean jaPossuiEsseTipo = repository.existsByFuncionarioIdAndTypeAndIdNot(equipamentoDto.getFuncionarioId(), equipamentoDto.getType(), id);

            if (jaPossuiEsseTipo) {
                throw new IllegalStateException("O funcionário já possui esse tipo.");
            }

            updateDate(equipamento, equipamentoDto);

            return repository.save(equipamento);

        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Equipamento não encontrado no banco de dados.");
        }
    }

    private void updateDate(Equipamento entity, EquipamentoDto equipamentoDto) {
        entity.setModel(equipamentoDto.getModel());
        entity.setType(equipamentoDto.getType());

        Funcionario funcionario = funcionarioRepository.findById(equipamentoDto.getFuncionarioId())
                .orElseThrow(() -> new NotFoundException("Funcionário não encontrado."));

        entity.setFuncionario(funcionario);
    }
}
