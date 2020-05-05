package com.github.edivaldoramos.service;

import com.github.edivaldoramos.model.Usuario;
import com.github.edivaldoramos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {
    private static final String MSG_PADRAO_USUARIO_NAO_ENCONTRADO = "Usuário não encontrado";
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
        Usuario usuario = repository.buscarPorNomeUsuario(nomeUsuario)
                .orElseThrow(() -> new UsernameNotFoundException(MSG_PADRAO_USUARIO_NAO_ENCONTRADO));

        String[] roles = usuario.getAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User.builder()
                .username("neo")
                .password(passwordEncoder.encode("matrix"))
                .roles(roles)
                .build();
    }

    @Transactional
    public void salvar(Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        repository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(MSG_PADRAO_USUARIO_NAO_ENCONTRADO));
    }

    public Usuario buscarPorNomeUsuario(String nomeUsuario) {
        return repository.buscarPorNomeUsuario(nomeUsuario)
                .orElseThrow(() -> new UsernameNotFoundException(MSG_PADRAO_USUARIO_NAO_ENCONTRADO));
    }

    public List<Usuario> buscarTodos() {
        return repository.findAll();
    }
}
