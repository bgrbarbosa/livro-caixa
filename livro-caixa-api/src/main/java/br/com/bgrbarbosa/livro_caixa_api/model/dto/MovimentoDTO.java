package br.com.bgrbarbosa.livro_caixa_api.model.dto;

import br.com.bgrbarbosa.livro_caixa_api.model.Lancamentos;
import br.com.bgrbarbosa.livro_caixa_api.model.enums.Status;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimentoDTO {

    private Long idMovimento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    private Date data;

    private String obs;

    @Enumerated(EnumType.STRING)
    private Status status;

    private List<Lancamentos> lancamentos;

    private Double totalCaixa;

    public Double getTotalCaixa() {
        if (lancamentos != null) {
            totalCaixa = lancamentos.stream()
                    .mapToDouble(Lancamentos::getValor)
                    .sum();
        } else {
            totalCaixa = 0.0;
        }
        return totalCaixa;
    }




}
