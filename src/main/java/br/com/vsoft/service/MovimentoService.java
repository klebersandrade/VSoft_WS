package br.com.vsoft.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.vsoft.enumerator.VagaStatus;
import br.com.vsoft.exception.ResourceNotFoundException;
import br.com.vsoft.model.Cliente;
import br.com.vsoft.model.Estaciona;
import br.com.vsoft.model.Movimento;
import br.com.vsoft.model.Vaga;
import br.com.vsoft.repository.ClienteRepository;
import br.com.vsoft.repository.MovimentoRepository;
import br.com.vsoft.repository.VagaRepository;

@Service
public class MovimentoService {
	@Autowired
	MovimentoRepository movimentoRepository;
	@Autowired
	VagaRepository vagaRepository;
	@Autowired
	ClienteRepository clienteRepository;

	public ResponseEntity<Object> getMovimentos(){
		return new ResponseEntity<Object>(movimentoRepository.findAll(), HttpStatus.OK);
	}
	
	public ResponseEntity<Object> getMovimentosByVaga(Long id){
		return new ResponseEntity<Object>(vagaRepository.findById(id).map(vaga -> {	
			Movimento movimento = movimentoRepository.findByVaga(vaga);
			movimento.setDataSaida(new Date());
			int horasExtras = calcQtdHorasExtras(movimento);
			if(horasExtras >= 3) {
				horasExtras = horasExtras - 2;
				movimento.setQtdHorasExtras(horasExtras);
				movimento.setValorHorasExtras(3.000 * horasExtras);	
			}			
			movimento.setValorPermanencia(7.000);
			movimento.setValorTotal(movimento.getValorPermanencia() + movimento.getValorHorasExtras());
			return movimento;
		}).orElseThrow(() -> new ResourceNotFoundException("Vaga:  "+ id + " não encontrada!"))
				, HttpStatus.OK);
	}
	
	public ResponseEntity<Object> getMovimentoAbertoById(Long id){
		Movimento movimento = movimentoRepository.findById(id).get();
		movimento.setDataSaida(new Date());
		int horasExtras = calcQtdHorasExtras(movimento);
		if(horasExtras >= 3) {
			horasExtras = horasExtras - 2;
			movimento.setQtdHorasExtras(horasExtras);
			movimento.setValorHorasExtras(3.000 * horasExtras);	
		}			
		movimento.setValorPermanencia(7.000);
		movimento.setValorTotal(movimento.getValorPermanencia() + movimento.getValorHorasExtras());
		return new ResponseEntity<Object>(movimento, HttpStatus.OK);
	}

//	Metodo para gerar os relatórios
	public ResponseEntity<Object> getMovimentos(Date dataInicial, Date dataFinal){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		String strDataIni = dateFormat.format(dataInicial);  
		String strDataFin = dateFormat.format(dataFinal);  
		return new ResponseEntity<Object>(movimentoRepository.getMovimentosPeriodo(strDataIni, strDataFin), HttpStatus.OK);
	}
	
	public ResponseEntity<Object> getMovimentoById(Long id){
		return new ResponseEntity<Object>(movimentoRepository.findById(id).map(movimento -> {
			return movimento;
		}).orElseThrow(() -> new ResourceNotFoundException("Movimento:  "+ id + " não encontrado!"))
				, HttpStatus.OK);
	}
	
	public ResponseEntity<Object> setMovimento(Movimento movimento){
		return new ResponseEntity<Object>(movimentoRepository.save(movimento), HttpStatus.OK);
	}
	
//	Estacionar em uma vaga
	public ResponseEntity<Object> setEstaciona(Estaciona estaciona){		
		Vaga vaga = vagaRepository.findByNumero(estaciona.getVagaNumero());
		if(vaga == null) {
			return new ResponseEntity<Object>("Vaga: "+estaciona.getVagaNumero()+" não encontrada!", HttpStatus.NO_CONTENT);
		}
		Cliente cliente = clienteRepository.findByPlaca(estaciona.getClientePlaca());
		if(cliente == null) {
			cliente = new Cliente();
			cliente.setPlaca(estaciona.getClientePlaca());
			cliente.setDescricao(estaciona.getClienteDescricao());
			
			cliente = clienteRepository.save(cliente);
		}
		
		vaga.setStatus(VagaStatus.OCUPADA);
		vaga = vagaRepository.save(vaga);
		Movimento movimento = new Movimento();
		movimento.setCliente(cliente);
		movimento.setVaga(vaga);
		movimento.setDataEntrada(new Date());
		movimento.setQtdHorasExtras(0);
		movimento.setValorHorasExtras(0.00);
		movimento.setValorPermanencia(0.00);
		movimento.setValorTotal(0.00);
		return new ResponseEntity<Object>(movimentoRepository.save(movimento), HttpStatus.OK);
	}
	
//	Pagar o Ticket
	public ResponseEntity<Object> setPagarTicket(Movimento mov){
		Movimento movimento = movimentoRepository.findById(mov.getId()).get();
		movimento.setDataSaida(mov.getDataSaida());
		movimento.setQtdHorasExtras(mov.getQtdHorasExtras());
		movimento.setValorHorasExtras(mov.getValorHorasExtras());				
		movimento.setValorPermanencia(mov.getValorPermanencia());
		movimento.setValorTotal(mov.getValorTotal());
		
		Vaga vaga = vagaRepository.findByNumero(mov.getVaga().getNumero());
		vaga.setStatus(VagaStatus.LIVRE);
		vaga = vagaRepository.save(vaga);
		
		return new ResponseEntity<Object>(movimentoRepository.save(movimento), HttpStatus.OK);
	}
	
	private int calcQtdHorasExtras(Movimento movimento) {
		Date dataEntrada = movimento.getDataEntrada();
		Date dataSaida = movimento.getDataSaida();
		long horas = (dataSaida.getTime() - dataEntrada.getTime()) / 3600000; 
		return (int)horas;
	}
}
