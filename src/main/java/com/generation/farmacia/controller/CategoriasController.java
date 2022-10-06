package com.generation.farmacia.controller;


import com.generation.farmacia.model.CategoriasModel;
import com.generation.farmacia.model.ProdutosModel;
import com.generation.farmacia.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriasController {
    @Autowired
    private CategoriasRepository categoriasRepository;

    @GetMapping
    public ResponseEntity<List<CategoriasModel>> getAll(){
        return ResponseEntity.ok(categoriasRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriasModel> getById(@PathVariable Long id){
        return categoriasRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<CategoriasModel>> getByNome(@PathVariable String descricao){
        return ResponseEntity.ok(categoriasRepository.findAllByDescricaoContainingIgnoreCase(descricao));
    }

    @PostMapping
    public ResponseEntity<CategoriasModel>post(@Valid @RequestBody CategoriasModel categoriasModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriasRepository.save(categoriasModel));
    }

    @PutMapping
    public ResponseEntity<CategoriasModel> put(@Valid @RequestBody CategoriasModel categoriasModel){
        return categoriasRepository.findById(categoriasModel.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(categoriasRepository.save(categoriasModel)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        Optional<CategoriasModel> categoriasModel = categoriasRepository.findById(id);
        if(categoriasModel.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        categoriasRepository.deleteById(id);
    }

}
