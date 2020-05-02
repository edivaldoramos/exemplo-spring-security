package com.github.edivaldoramos.controller;

import com.github.edivaldoramos.model.Empresa;
import com.github.edivaldoramos.service.EmpresaService;
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
        try {
            empresaService.salvar(empresa);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("error", e.getMessage()).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Empresa> buscarPorId(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(empresaService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("error", e.getMessage()).build();
        }
    }

    @GetMapping(value = "cnpjRaiz")
    public ResponseEntity<Empresa> buscarPorId(@RequestParam("cnpjRaiz") String cnpjRaiz){
        try {
            return ResponseEntity.ok(empresaService.buscarPorCnpj(cnpjRaiz));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("error", e.getMessage()).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> buscarTodos(){
        try {
            return ResponseEntity.ok(empresaService.buscarPorTodos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("error", e.getMessage()).build();
        }
    }
}
