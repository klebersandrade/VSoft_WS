package br.com.vsoft.model;

import javax.validation.constraints.NotNull;

public class VagaSituacao {

	@NotNull
	private Vaga vaga;
	@NotNull
	private Movimento movimento;
	public Vaga getVaga() {
		return vaga;
	}
	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}
	public Movimento getMovimento() {
		return movimento;
	}
	public void setMovimento(Movimento movimento) {
		this.movimento = movimento;
	}
}
