package br.com.vsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vsoft.model.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
	
	Vaga findByNumero(String placa);

}
