package br.com.vsoft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.vsoft.enumerator.VagaStatus;

@Entity
@Table(name = "tb_vaga")
public class Vaga {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(max = 10)
	@Column(length = 10, nullable = false, unique = true)
	private String numero;
	@NotNull
	@Column(nullable = false)
	private VagaStatus status;
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public VagaStatus getStatus() {
		return status;
	}
	public void setStatus(VagaStatus status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
