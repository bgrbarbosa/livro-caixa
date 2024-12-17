package br.com.bgrbarbosa.livro_caixa_api.controller;

import br.com.bgrbarbosa.livro_caixa_api.controller.exception.ResourceExceptionHandler;
import br.com.bgrbarbosa.livro_caixa_api.model.Lancamentos;
import br.com.bgrbarbosa.livro_caixa_api.model.dto.LancamentoDTO;
import br.com.bgrbarbosa.livro_caixa_api.model.mapper.LancamentoMapper;
import br.com.bgrbarbosa.livro_caixa_api.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lancamento")
public class LancamentoController {

    @Autowired
    private LancamentoService service;

    @Autowired
    private LancamentoMapper mapper;

    @GetMapping
    public ResponseEntity<Object> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarRegistroPorId(@PathVariable(value = "id")  Long id) {
        Optional<Lancamentos> obj = service.findById(id);
        if (!obj.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceExceptionHandler());
        }
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(obj.get()));
    }

    @GetMapping("/period")
    public ResponseEntity<Object> buscarRegistroPorPeriodo(
            @RequestParam("dt_init") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dt_init,
            @RequestParam("dt_final") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dt_final){

        List<Lancamentos> obj = service.listarPerido(dt_init, dt_final);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDtoList(obj));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> salvar(@RequestBody @Valid LancamentoDTO dto) {
        Lancamentos registro = service.salvar(mapper.toEntity(dto));
        return ResponseEntity.ok().body(mapper.toDto(registro));
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<LancamentoDTO> update(@RequestBody @Valid LancamentoDTO dto) {
        Optional<Lancamentos> aux = service.findById(dto.getIdLancamento());
        if (aux.isPresent()) {
            Lancamentos registro = service.update(mapper.toEntity(dto));
            return ResponseEntity.ok().body(mapper.toDto(registro));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void  delete(@PathVariable Long id) {
        Optional<Lancamentos> aux = service.findById(id);
        if (aux.isPresent()) {
            service.delete(id);
        }
    }
}
