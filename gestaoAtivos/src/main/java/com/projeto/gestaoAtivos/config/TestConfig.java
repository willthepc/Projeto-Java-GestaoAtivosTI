package com.projeto.gestaoAtivos.config;

import com.projeto.gestaoAtivos.entities.Equipamento;
import com.projeto.gestaoAtivos.entities.Funcionario;
import com.projeto.gestaoAtivos.repositories.EquipamentoRepository;
import com.projeto.gestaoAtivos.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;


    @Override
    public void run(String... args) throws Exception {
        Funcionario f1 = new Funcionario(null, "William", "Desenvolvimento", "william@empresa.com");
        Funcionario f2 = new Funcionario(null, "Mari Medeiros", "Suporte", "mari.medeiros@empresa.com");
        Funcionario f3 = new Funcionario(null, "Bruno", "QA", "bruno@empresa.com");
        Funcionario f4 = new Funcionario(null, "Fernando", "Gestão", "fernando@empresa.com");

        funcionarioRepository.saveAll(Arrays.asList(f1, f2, f3, f4));
    }
}

