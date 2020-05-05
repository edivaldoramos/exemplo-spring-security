package com.github.edivaldoramos.service;

import com.github.edivaldoramos.exceptions.RecursoNaoEncontradoException;
import com.github.edivaldoramos.model.Filial;
import com.github.edivaldoramos.repository.FilialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilialService implements IFilialService {
    private final FilialRepository repository;

    @Override
    @Transactional
    public void salvar(Filial filial) {
        repository.save(filial);
    }

    @Override
    public Filial buscarPorId(Long id) throws RecursoNaoEncontradoException {
        Optional<Filial> filial = repository.findById(id);
        return filial.orElseThrow(() ->
                new RecursoNaoEncontradoException("Filial não encontrada para o id "+id));
    }

    @Override
    public Filial buscarPorCnpj(String cnpj) throws RecursoNaoEncontradoException {
        Optional<Filial> filial = repository.buscarPorCnpj(cnpj);
        return filial.orElseThrow(() ->
                new RecursoNaoEncontradoException("Filial não encontrada para o cnpj "+cnpj));
    }

    @Override
    public List<Filial> buscarTodos() {
        return repository.findAll();
    }
}
