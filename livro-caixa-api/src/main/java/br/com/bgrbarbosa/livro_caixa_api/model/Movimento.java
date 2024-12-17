package br.com.bgrbarbosa.livro_caixa_api.model;


import br.com.bgrbarbosa.livro_caixa_api.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_movimento_caixa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimento;

    @Column
    private Date data;

    @Column
    private String obs;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "movimento" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Lancamentos> lancamentos;

}
