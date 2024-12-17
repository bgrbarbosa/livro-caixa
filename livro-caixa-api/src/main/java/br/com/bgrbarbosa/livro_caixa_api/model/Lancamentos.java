package br.com.bgrbarbosa.livro_caixa_api.model;


import br.com.bgrbarbosa.livro_caixa_api.model.enums.Tipo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_lancamentos_caixa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lancamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLancamento;

    @Column
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column
    private String descricao;

    @Column
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "idMovimento")
    private Movimento movimento;


}


