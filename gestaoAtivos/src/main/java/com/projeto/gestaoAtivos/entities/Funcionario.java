package com.projeto.gestaoAtivos.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import tools.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private String department;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<Equipamento> equipamentoList = new ArrayList<>();

    @Builder
    public Funcionario(Long id, String name, String department, String email) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.email = email;
    }

    public List<String> getTiposEquipamento() {
        return equipamentoList.stream()
                .map(equipamento -> equipamento.getType())
                .toList();
    }
}
