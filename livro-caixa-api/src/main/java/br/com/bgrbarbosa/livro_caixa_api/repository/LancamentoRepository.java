package br.com.bgrbarbosa.livro_caixa_api.repository;

import br.com.bgrbarbosa.livro_caixa_api.model.Lancamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamentos, Long> {

    //Lancamentos findTopByOrderByIdDesc();

    @Query(value = "SELECT * FROM TB_LANCAMENTOS_CAIXA lc WHERE lc.data BETWEEN :dt_init AND :dt_final", nativeQuery = true)
    List<Lancamentos> findByPeriod(Date dt_init, Date dt_final);
}
