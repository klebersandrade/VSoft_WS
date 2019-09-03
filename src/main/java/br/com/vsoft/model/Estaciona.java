package br.com.vsoft.model;

import javax.validation.constraints.NotNull;

public class Estaciona {
	@NotNull
	private String vagaNumero;
	@NotNull
	private String clientePlaca;
	private String clienteDescricao;
	public String getVagaNumero() {
		return vagaNumero;
	}
	public void setVagaNumero(String vagaNumero) {
		this.vagaNumero = vagaNumero;
	}
	public String getClientePlaca() {
		return clientePlaca;
	}
	public void setClientePlaca(String clientePlaca) {
		this.clientePlaca = clientePlaca;
	}
	public String getClienteDescricao() {
		return clienteDescricao;
	}
	public void setClienteDescricao(String clienteDescricao) {
		this.clienteDescricao = clienteDescricao;
	}
}
