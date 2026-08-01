package com.projeto.gestaoAtivos.resources;

import com.projeto.gestaoAtivos.dto.EquipamentoDto;
import com.projeto.gestaoAtivos.entities.Equipamento;
import com.projeto.gestaoAtivos.entities.Funcionario;
import com.projeto.gestaoAtivos.services.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/equipamentos")
public class EquipamentoResource {
   @Autowired
    private EquipamentoService service;

   @GetMapping
   public ResponseEntity<List<Equipamento>> findAll() {
       List<Equipamento> list = service.findAll();
       return ResponseEntity.ok().body(list);
   }

   @GetMapping(value = "/{id}")
    public ResponseEntity<Equipamento> findById(@PathVariable Long id) {
       Equipamento equipamento = service.findById(id);
       return ResponseEntity.ok().body(equipamento);
   }

    @PostMapping
    public ResponseEntity insert(@RequestBody EquipamentoDto equipamentoDto){
        Equipamento equipamento = service.insert(equipamentoDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(equipamento.getId()).toUri();

        return ResponseEntity.created(uri).body(equipamento);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Equipamento> delete(@PathVariable Long id) {
       service.delete(id);
       return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody EquipamentoDto equipamentoDto) {
       Equipamento equipamento = service.update(id, equipamentoDto);

       return ResponseEntity.ok().body(equipamento);
    }
}
