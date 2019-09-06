package br.com.vsoft.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vsoft.model.Estaciona;
import br.com.vsoft.model.Movimento;
import br.com.vsoft.service.MovimentoService;

@RestController
public class MovimentoController {
	@Autowired
	MovimentoService movimentoService;
	
	@GetMapping("/movimentos/{id}")
	public ResponseEntity<Object> getMovimento(@PathVariable Long id) {
		return movimentoService.getMovimentoById(id);
	}
	
	@GetMapping("/movimentosbyvaga/{id}")
	public ResponseEntity<Object> getMovimentoByVaga(@PathVariable Long id) {
		return movimentoService.getMovimentosByVaga(id);
	}
	
	@GetMapping("/movimentoabertobyid/{id}")
	public ResponseEntity<Object> getMovimentoAbertoById(@PathVariable Long id) {
		return movimentoService.getMovimentoAbertoById(id);
	}
	
	@GetMapping("/movimentos")
	public ResponseEntity<Object> getMovimentos() {
		return movimentoService.getMovimentos();
	}
	
//	Método para gerar os relatórios
	@GetMapping("/movimentos/{dataInicial}/{dataFinal}")
	public ResponseEntity<Object> getMovimentosPeriodo(@PathVariable Date dataInicial, @PathVariable Date dataFinal) {
		return movimentoService.getMovimentos(dataInicial, dataFinal);
	}
	
	@PostMapping("/movimentos")
	public ResponseEntity<Object> setMovimento(@Valid @RequestBody Movimento movimento) {
		return movimentoService.setMovimento(movimento);
	}
//	Estacionar veículo
	@PostMapping("/estaciona")
	public ResponseEntity<Object> setEstaciona(@Valid @RequestBody Estaciona estaciona) {
		return movimentoService.setEstaciona(estaciona);
	}
//	Pagar o ticket
	@PostMapping("/pagaticket")
	public ResponseEntity<Object> setPagaTicket(@Valid @RequestBody Movimento movimento) {
		return movimentoService.setPagarTicket(movimento);
	}
	
}
