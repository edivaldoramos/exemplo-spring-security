package com.github.edivaldoramos.controller;

import com.github.edivaldoramos.exceptions.RecursoNaoEncontradoException;
import com.github.edivaldoramos.model.Empresa;
import com.github.edivaldoramos.service.IEmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
public class EmpresaController {
    private final IEmpresaService empresaService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Empresa> criar(@RequestBody @Valid Empresa empresa) {
        empresaService.salvar(empresa);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Empresa> buscarPorId(@PathVariable("id") Long id) throws RecursoNaoEncontradoException {
        return ResponseEntity.ok(empresaService.buscarPorId(id));
    }

    @GetMapping(value = "cnpjRaiz")
    public ResponseEntity<Empresa> buscarPorCnpj(@RequestParam("cnpjRaiz") String cnpjRaiz) throws RecursoNaoEncontradoException {
        return ResponseEntity.ok(empresaService.buscarPorCnpj(cnpjRaiz));
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> buscarTodos() {
        return ResponseEntity.ok(empresaService.buscarPorTodos());
    }
}
