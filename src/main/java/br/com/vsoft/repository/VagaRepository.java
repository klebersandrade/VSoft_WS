package br.com.vsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.vsoft.model.Vaga;
import br.com.vsoft.model.VagasQtd;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
	
	Vaga findByNumero(String placa);
	@Query(
			value = "SELECT COALESCE(SUM(1),0) AS QTD_TOTAL, COALESCE(SUM(CASE WHEN \r\n" + 
					"V.STATUS=0 THEN 1 ELSE 0 END),0) AS QTD_LIVRE FROM TB_VAGA V",
			nativeQuery = true
	)
	VagasQtd getQtdVagas();
}
