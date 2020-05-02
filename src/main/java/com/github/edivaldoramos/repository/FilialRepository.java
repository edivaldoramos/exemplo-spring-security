package com.github.edivaldoramos.repository;

import com.github.edivaldoramos.model.Filial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Long> {
    @Query("SELECT f FROM Filial f WHERE f.cnpj = :cnpj")
    Optional<Filial> buscarPorCnpj(String cnpj);
}
