package org.exemplo.persistencia.database.model;

import java.io.Serializable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.exemplo.persistencia.database.enumeration.TipoTransacao;

@javax.persistence.Entity
@Table(name = "registro_transacao")
public class RegistroTransacao  implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "valor")
	private BigDecimal valor;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private TipoTransacao tipo;
	@Column(name = "data")
	private LocalDateTime data;
	
	@ManyToOne
	@JoinColumn(name = "conta_id")
	private Conta conta;
	
	public RegistroTransacao() {
		
	}
	
	public RegistroTransacao(BigDecimal valor, TipoTransacao tipo, LocalDateTime data) {
		this.valor = valor;
		this.tipo = tipo;
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoTransacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoTransacao tipo) {
		this.tipo = tipo;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, id, tipo, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroTransacao other = (RegistroTransacao) obj;
		return Objects.equals(data, other.data) && Objects.equals(id, other.id) && tipo == other.tipo
				&& Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return "RegistroTransacao [id=" + id + ", valor=" + valor + ", tipo=" + tipo + ", data=" + data + "]";
	}

	

	public void setConta(int numeroConta) {
		// TODO Auto-generated method stub
		
	}

	public void setConta(Conta conta) {
		this.conta = conta;
		
	}
	
}
