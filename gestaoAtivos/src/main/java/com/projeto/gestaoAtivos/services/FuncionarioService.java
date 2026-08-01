package com.projeto.gestaoAtivos.services;

import com.projeto.gestaoAtivos.dto.FuncionarioDto;
import com.projeto.gestaoAtivos.entities.Funcionario;
import com.projeto.gestaoAtivos.exception.NotFoundException;
import com.projeto.gestaoAtivos.repositories.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public List<Funcionario> findAll() {
        return repository.findAll();
    }

    public Funcionario findById(Long id) {
        Optional<Funcionario> obj = Optional.of(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Funcionário não encontrado!")));
        return obj.get();
    }

    public Funcionario insert(FuncionarioDto funcionarioDto) {
        Funcionario novoFuncionario = Funcionario.builder()
                .name(funcionarioDto.getName())
                .department(funcionarioDto.getDepartment())
                .email(funcionarioDto.getEmail())
                .build();

        return repository.save(novoFuncionario);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Funcionário não encontrado no banco de dados.");
        }

        try {
            repository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("Não é possível deletar: há equipamentos vinculados.");
        }
    }

    public Funcionario update(Long id, FuncionarioDto obj) {
        try {
            Funcionario entity = repository.getReferenceById(id);
            updateDate(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Id não encontrado.");
        }
    }

    private void updateDate(Funcionario entity, FuncionarioDto funcionarioDto) {
        entity.setName(funcionarioDto.getName());
        entity.setEmail(funcionarioDto.getEmail());
        entity.setDepartment(funcionarioDto.getDepartment());
    }
}
