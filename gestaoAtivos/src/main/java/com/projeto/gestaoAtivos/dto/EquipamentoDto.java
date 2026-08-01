package com.projeto.gestaoAtivos.dto;

import com.projeto.gestaoAtivos.entities.Funcionario;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class EquipamentoDto {
    private Long id;
    private String model;
    private String type;
    private Long funcionarioId;
}
