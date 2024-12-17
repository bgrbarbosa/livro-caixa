package br.com.bgrbarbosa.livro_caixa_api.controller;

import br.com.bgrbarbosa.livro_caixa_api.controller.exception.ResourceExceptionHandler;
import br.com.bgrbarbosa.livro_caixa_api.model.Lancamentos;
import br.com.bgrbarbosa.livro_caixa_api.model.Movimento;
import br.com.bgrbarbosa.livro_caixa_api.model.dto.LancamentoDTO;
import br.com.bgrbarbosa.livro_caixa_api.model.dto.MovimentoDTO;
import br.com.bgrbarbosa.livro_caixa_api.model.mapper.LancamentoMapper;
import br.com.bgrbarbosa.livro_caixa_api.model.mapper.MovimentoMapper;
import br.com.bgrbarbosa.livro_caixa_api.service.LancamentoService;
import br.com.bgrbarbosa.livro_caixa_api.service.MovimentoService;
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
@RequestMapping("/api/movimento")
public class MovimentoController {

    @Autowired
    private MovimentoService service;

    @Autowired
    private MovimentoMapper mapper;

    @GetMapping
    public ResponseEntity<Object> listarTodos() {
        return ResponseEntity.ok().body(mapper.toDtoList(service.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarRegistroPorId(@PathVariable(value = "id")  Long id) {
        Optional<Movimento> obj = service.findById(id);
        if (!obj.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceExceptionHandler());
        }
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(obj.get()));
    }

    @GetMapping("/period")
    public ResponseEntity<Object> buscarRegistroPorPeriodo(
            @RequestParam("dt_init") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dt_init,
            @RequestParam("dt_final") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dt_final){

        List<Movimento> obj = service.listarPerido(dt_init, dt_final);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDtoList(obj));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object>salvar(@RequestBody @Valid MovimentoDTO dto) {
        Movimento registro = service.salvar(mapper.toEntity(dto));
        return ResponseEntity.ok().body(mapper.toDto(registro));
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MovimentoDTO>update(@RequestBody @Valid MovimentoDTO dto) {
        Optional<Movimento> aux = service.findById(dto.getIdMovimento());
        if (aux.isPresent()) {
            Movimento registro = service.update(mapper.toEntity(dto));
            return ResponseEntity.ok().body(mapper.toDto(registro));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object>delete(@PathVariable Long id) {
        Optional<Movimento> aux = service.findById(id);
        if (aux.isPresent()) {
            service.delete(id);
            return ResponseEntity.ok().body("Registro deletado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
