package br.com.vsoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.vsoft.model.Movimento;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {
	@Query(
			value = "SELECT M.* FROM TB_MOVIMENTO M WHERE M.DATASAIDA IS NULL",
			nativeQuery = true
	)
	List<Movimento> getMovimentosAbertos();
	
	@Query(
			value = "SELECT * FROM TB_MOVIMENTO M WHERE CAST(M.DATA_ENTRADA AS DATE) >= "+
					"'?1' AND CAST(M.DATA_ENTRADA AS DATE) <= '?2'",
			nativeQuery = true
	)
	List<Movimento> getMovimentosPeriodo(String dataInicial, String dataFinal);
}
