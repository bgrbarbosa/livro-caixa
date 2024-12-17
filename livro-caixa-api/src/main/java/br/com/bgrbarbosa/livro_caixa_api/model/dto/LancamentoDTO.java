package br.com.bgrbarbosa.livro_caixa_api.model.dto;

import br.com.bgrbarbosa.livro_caixa_api.model.Movimento;
import br.com.bgrbarbosa.livro_caixa_api.model.enums.Tipo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoDTO {

    private Long idLancamento;

    private Tipo tipo;

    private String descricao;

    private Double valor;

    private Movimento movimento;
}


