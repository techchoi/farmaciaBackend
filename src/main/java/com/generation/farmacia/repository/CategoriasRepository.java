package com.generation.farmacia.repository;

import com.generation.farmacia.model.CategoriasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriasRepository extends JpaRepository<CategoriasModel, Long> {
    public List<CategoriasModel> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);

}
