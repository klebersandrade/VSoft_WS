package br.com.vsoft.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vsoft.model.Vaga;
import br.com.vsoft.service.VagaService;

@RestController
public class VagaController {

	@Autowired
	VagaService vagaService;
	
	@GetMapping("/vagas/{id}")
	public ResponseEntity<Object> getVaga(@PathVariable Long id) {
		return vagaService.getVagaById(id);
	}
	
	@GetMapping("/vagas")
	public ResponseEntity<Object> getVagas() {
		return vagaService.getVagas();
	}
	
	@PostMapping("/vagas")
	public ResponseEntity<Object> setVaga(@Valid @RequestBody Vaga vaga) {
		return vagaService.setVaga(vaga);
	}
	
//	Pegando situações das vagas
//	Aqui retorna tanto a situação (Livre|Ocupado), quando o movimento (Atual ocupação) dessa vaga
	@GetMapping("/vagas/situacoes")
	public ResponseEntity<Object> getVagasSituacoes() {
		return vagaService.getVagaSituacoes();
	}
	
//	Método específico para retornar a quantidade de vagas e a disponibilidade
	@GetMapping("/vagas/qtdvagas")
	public ResponseEntity<Object> getQtdVagas(){
		return vagaService.getVagaQtd();
	}
}
