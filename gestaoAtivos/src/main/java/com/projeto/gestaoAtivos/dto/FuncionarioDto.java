package com.projeto.gestaoAtivos.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FuncionarioDto {
    private Long id;
    private String name;
    private String department;
    private String email;
}