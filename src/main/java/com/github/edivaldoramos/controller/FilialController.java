package com.github.edivaldoramos.controller;

import com.github.edivaldoramos.exceptions.RecursoNaoEncontradoException;
import com.github.edivaldoramos.model.Empresa;
import com.github.edivaldoramos.model.Filial;
import com.github.edivaldoramos.service.IFilialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filiais")
@RequiredArgsConstructor
public class FilialController {
    private final IFilialService filialService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Empresa> criar(@RequestBody Filial filial) {
        filialService.salvar(filial);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Filial> buscarPorId(@PathVariable("id") Long id) throws RecursoNaoEncontradoException {
        return ResponseEntity.ok(filialService.buscarPorId(id));
    }

    @GetMapping(value = "cnpj")
    public ResponseEntity<Filial> buscarPorId(@RequestParam("cnpj") String cnpjRaiz) throws RecursoNaoEncontradoException {
        return ResponseEntity.ok(filialService.buscarPorCnpj(cnpjRaiz));
    }

    @GetMapping
    public ResponseEntity<List<Filial>> buscarTodos() {
        return ResponseEntity.ok(filialService.buscarTodos());
    }
}
