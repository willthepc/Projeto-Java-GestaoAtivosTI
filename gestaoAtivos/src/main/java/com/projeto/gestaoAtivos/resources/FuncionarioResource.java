package com.projeto.gestaoAtivos.resources;

import com.projeto.gestaoAtivos.dto.FuncionarioDto;
import com.projeto.gestaoAtivos.entities.Funcionario;
import com.projeto.gestaoAtivos.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioResource {
    @Autowired
    private FuncionarioService service;

   @GetMapping
   public ResponseEntity<List<Funcionario>> findAll() {
       List<Funcionario> list = service.findAll();
       return ResponseEntity.ok().body(list);
   }

   @GetMapping(value = "/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
        Funcionario funcionario = service.findById(id);
        return ResponseEntity.ok().body(funcionario);
   }

    @PostMapping
    public ResponseEntity insert(@RequestBody FuncionarioDto funcionarioDto) {
        Funcionario funcionario = service.insert(funcionarioDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(funcionario.getId()).toUri();

        return ResponseEntity.created(uri).body(funcionario);
    }

   @DeleteMapping(value = "/{id}")
    public ResponseEntity<Funcionario> delete(@PathVariable Long id) {
         service.delete(id);
         return ResponseEntity.noContent().build();

   }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody FuncionarioDto funcionarioDto) {
       Funcionario funcionario = service.update(id, funcionarioDto);

       return ResponseEntity.ok().body(funcionario);
    }
}
