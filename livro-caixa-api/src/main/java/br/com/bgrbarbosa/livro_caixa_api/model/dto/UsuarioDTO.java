package br.com.bgrbarbosa.livro_caixa_api.model.dto;

import br.com.bgrbarbosa.livro_caixa_api.model.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private List<Perfil> perfis;
    private LocalDate dataCriacao = LocalDate.now();
}
