package br.com.vsoft.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.vsoft.exception.ResourceNotFoundException;
import br.com.vsoft.model.Movimento;
import br.com.vsoft.model.Vaga;
import br.com.vsoft.model.VagaSituacao;
import br.com.vsoft.repository.MovimentoRepository;
import br.com.vsoft.repository.VagaRepository;

@Service
public class VagaService {

	@Autowired
	VagaRepository vagaRepository;
	@Autowired
	MovimentoRepository movimentoRepository;

	public ResponseEntity<Object> getVagas(){
		return new ResponseEntity<Object>(vagaRepository.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<Object> getVagaById(Long id){
		return new ResponseEntity<Object>(vagaRepository.findById(id).map(vaga -> {
			return vaga;
		}).orElseThrow(() -> new ResourceNotFoundException("Vaga:  "+ id + " não encontrada!"))
				, HttpStatus.OK);
	}
	
	public ResponseEntity<Object> setVaga(Vaga vaga){
		return new ResponseEntity<Object>(vagaRepository.save(vaga), HttpStatus.OK);
	}
	
	public ResponseEntity<Object> getVagaSituacoes(){
		
		List<Vaga> listaVagas = vagaRepository.findAll();
		List<Movimento> listaMovimentos = movimentoRepository.getMovimentosAbertos();
		
		List<VagaSituacao> listaRetorno = new ArrayList<VagaSituacao>();
		
		for (int i = 0; i < listaVagas.size(); i++) {
			VagaSituacao retorno = new VagaSituacao();
			retorno.setVaga(listaVagas.get(i));

			Movimento movimento = listaMovimentos.stream()
					  .filter(mov -> retorno.getVaga().equals(mov.getVaga()))
					  .findAny()
					  .orElse(null);
			
			retorno.setMovimento(movimento);
			
			listaRetorno.add(retorno);
		}
		
		return new ResponseEntity<Object>(listaRetorno, HttpStatus.OK);
	}
	public ResponseEntity<Object> getVagaQtd(){
		return new ResponseEntity<Object>(vagaRepository.getQtdVagas(), HttpStatus.OK);
	}
}
