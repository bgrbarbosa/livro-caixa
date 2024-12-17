package br.com.bgrbarbosa.livro_caixa_api.service;

import br.com.bgrbarbosa.livro_caixa_api.model.Lancamentos;
import br.com.bgrbarbosa.livro_caixa_api.model.Movimento;
import br.com.bgrbarbosa.livro_caixa_api.repository.MovimentoRepository;
import br.com.bgrbarbosa.livro_caixa_api.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentoService {

    private List<Lancamentos> lancamentos;

    @Autowired
    private MovimentoRepository repository;

    @Transactional
    public Movimento salvar(Movimento movimento) {
        return repository.save(movimento);
    }

    @Transactional
    public List<Movimento> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public Optional<Movimento> findById(Long id) {
        Optional<Movimento> aux =  repository.findById(id);
        if (!aux.isPresent()) {
            throw new ResourceNotFoundException("Entity not found: " + id);
        }
        return aux;
    }

    @Transactional
    public List<Movimento> listarPerido(Date dt_init, Date dt_final) {
        return repository.findByPeriod(dt_init, dt_final);
    }

    @Transactional
    public Movimento update(Movimento entity) {
        Optional<Movimento> aux =  repository.findById(entity.getIdMovimento());
        if (!aux.isPresent()) {
            throw new ResourceNotFoundException("Entity not found: " + entity.getIdMovimento());
        } else{
            Movimento registro =  repository.getOne(entity.getIdMovimento());
            registro.setData(entity.getData());
            registro.setObs(entity.getObs());
            registro.setStatus(entity.getStatus());
            return registro;
        }
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
