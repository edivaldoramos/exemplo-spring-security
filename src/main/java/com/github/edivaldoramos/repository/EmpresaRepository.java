package com.github.edivaldoramos.repository;

import com.github.edivaldoramos.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    @Query("SELECT e FROM Empresa e WHERE e.cnpjRaiz = :cnpjRaiz")
    Optional<Empresa> buscarPorCnpj(String cnpjRaiz);
}
