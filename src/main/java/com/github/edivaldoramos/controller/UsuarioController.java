package com.github.edivaldoramos.controller;

import com.github.edivaldoramos.model.Usuario;
import com.github.edivaldoramos.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        usuarioService.salvar(usuario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @GetMapping(value = "nomeUsuario")
    public ResponseEntity<Usuario> buscarPorId(@RequestParam("nomeUsuario") String nomeUsuario) {
        return ResponseEntity.ok(usuarioService.buscarPorNomeUsuario(nomeUsuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        return ResponseEntity.ok(usuarioService.buscarTodos());
    }
}
