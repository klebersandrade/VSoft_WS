package br.com.vsoft.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "tb_movimento")
public class Movimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "vaga_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
	private Vaga vaga;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
	private Cliente cliente;
	@NotNull
	@Column(nullable = false)
	private Date dataEntrada;
	@Column
	private Date dataSaida;
	@Column
	private Double valorPermanencia;
	@Column
	private Integer qtdHorasExtras;
	@Column
	private Double valorHorasExtras;
	@Column
	private Double valorTotal;
	public Vaga getVaga() {
		return vaga;
	}
	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public Date getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Double getValorPermanencia() {
		return valorPermanencia;
	}
	public void setValorPermanencia(Double valorPermanencia) {
		this.valorPermanencia = valorPermanencia;
	}
	public Integer getQtdHorasExtras() {
		return qtdHorasExtras;
	}
	public void setQtdHorasExtras(Integer qtdHorasExtras) {
		this.qtdHorasExtras = qtdHorasExtras;
	}
	public Double getValorHorasExtras() {
		return valorHorasExtras;
	}
	public void setValorHorasExtras(Double valorHorasExtras) {
		this.valorHorasExtras = valorHorasExtras;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
