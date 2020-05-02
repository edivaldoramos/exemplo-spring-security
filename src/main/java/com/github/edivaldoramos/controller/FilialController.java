package com.github.edivaldoramos.controller;

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
        try {
            filialService.salvar(filial);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("error", e.getMessage()).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Filial> buscarPorId(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(filialService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("error", e.getMessage()).build();
        }
    }

    @GetMapping(value = "cnpj")
    public ResponseEntity<Filial> buscarPorId(@RequestParam("cnpj") String cnpjRaiz){
        try {
            return ResponseEntity.ok(filialService.buscarPorCnpj(cnpjRaiz));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("error", e.getMessage()).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Filial>> buscarTodos(){
        try {
            return ResponseEntity.ok(filialService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("error", e.getMessage()).build();
        }
    }
}
