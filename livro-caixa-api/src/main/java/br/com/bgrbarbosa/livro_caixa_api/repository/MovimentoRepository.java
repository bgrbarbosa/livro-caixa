package br.com.bgrbarbosa.livro_caixa_api.repository;

import br.com.bgrbarbosa.livro_caixa_api.model.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MovimentoRepository extends JpaRepository<Movimento, Long> {

    @Query(value = "SELECT * FROM TB_MOVIMENTO_CAIXA mc WHERE mc.data BETWEEN :dt_init AND :dt_final", nativeQuery = true)
    List<Movimento> findByPeriod(Date dt_init, Date dt_final);
}
