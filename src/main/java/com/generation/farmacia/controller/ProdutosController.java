package com.generation.farmacia.controller;

import com.generation.farmacia.model.CategoriasModel;
import com.generation.farmacia.model.ProdutosModel;
import com.generation.farmacia.repository.CategoriasRepository;
import com.generation.farmacia.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutosController {
    @Autowired
    private ProdutosRepository produtosRepository;
    @Autowired
    private CategoriasRepository categoriasRepository;

    @GetMapping
    public ResponseEntity<List<ProdutosModel>> getAll(){
        return ResponseEntity.ok(produtosRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutosModel> getById(@PathVariable Long id){
        return produtosRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ProdutosModel>> getByDescricao(@PathVariable String nome){
        return ResponseEntity.ok(produtosRepository.findAllByNomeContainingIgnoreCase(nome));

    }

    @PostMapping
    public ResponseEntity<ProdutosModel>post(@Valid @RequestBody ProdutosModel produtosModel){
        if (categoriasRepository.existsById(produtosModel.getCategoriasModel().getId()))
            return ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository.save(produtosModel));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping
    public ResponseEntity<ProdutosModel> put(@Valid @RequestBody ProdutosModel produtosModel){
        if (produtosRepository.existsById(produtosModel.getId())){
            if(categoriasRepository.existsById(produtosModel.getCategoriasModel().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(produtosRepository.save(produtosModel));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

     @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<ProdutosModel> produtos = produtosRepository.findById(id);
        if(produtos.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        produtosRepository.deleteById(id);

     }

}
