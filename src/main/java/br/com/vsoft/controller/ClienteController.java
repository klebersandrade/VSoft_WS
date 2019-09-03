package br.com.vsoft.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vsoft.model.Cliente;
import br.com.vsoft.service.ClienteService;

@RestController
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<Object> getCliente(@PathVariable Long id) {
		return clienteService.getClienteById(id);
	}
	
	@GetMapping("/clientes")
	public ResponseEntity<Object> getClientes() {
		return clienteService.getClientes();
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<Object> setCliente(@Valid @RequestBody Cliente cliente) {
		return clienteService.setCliente(cliente);
	}
}
