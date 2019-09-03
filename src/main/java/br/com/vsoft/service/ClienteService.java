package br.com.vsoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.vsoft.exception.ResourceNotFoundException;
import br.com.vsoft.model.Cliente;
import br.com.vsoft.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;

	public ResponseEntity<Object> getClientes(){
		return new ResponseEntity<Object>(clienteRepository.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<Object> getClienteById(Long id){
		return new ResponseEntity<Object>(clienteRepository.findById(id).map(cliente -> {
			return cliente;
		}).orElseThrow(() -> new ResourceNotFoundException("Cliente:  "+ id + " não encontrado!"))
				, HttpStatus.OK);
	}
	
	public ResponseEntity<Object> setCliente(Cliente cliente){
		return new ResponseEntity<Object>(clienteRepository.save(cliente), HttpStatus.OK);
	}
}
