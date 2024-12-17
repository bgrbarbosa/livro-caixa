package br.com.bgrbarbosa.livro_caixa_api.service;

import br.com.bgrbarbosa.livro_caixa_api.model.Lancamentos;
import br.com.bgrbarbosa.livro_caixa_api.model.enums.Tipo;
import br.com.bgrbarbosa.livro_caixa_api.repository.LancamentoRepository;
import br.com.bgrbarbosa.livro_caixa_api.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository repository;

    @Transactional
    public Lancamentos salvar(Lancamentos lancamento) {
        lancamento.setValor(lancamento.getTipo().equals(Tipo.SAIDA) ? -lancamento.getValor() : lancamento.getValor());
        return repository.save(lancamento);
    }

    @Transactional
    public List<Lancamentos> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public Optional<Lancamentos> findById(Long id) {
        Optional<Lancamentos> aux =  repository.findById(id);
        if (!aux.isPresent()) {
            throw new ResourceNotFoundException("Entity not found: " + id);
        }
        return aux;
    }

    @Transactional
    public List<Lancamentos> listarPerido(Date dt_init, Date dt_final) {
        return repository.findByPeriod(dt_init, dt_final);
    }

    @Transactional
    public Lancamentos update(Lancamentos entity) {
        Lancamentos aux = repository.findById(entity.getIdLancamento())
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found: " + entity.getIdLancamento()));
        aux.setIdLancamento(entity.getIdLancamento());
        aux.setTipo(entity.getTipo());
        aux.setDescricao(entity.getDescricao());
        aux.setValor(entity.getValor());
        aux.setMovimento(entity.getMovimento());
        return repository.save(aux);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
