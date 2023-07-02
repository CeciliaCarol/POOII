package org.exemplo.persistencia.database.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.exemplo.persistencia.database.dao.IEntityDAO;
import org.exemplo.persistencia.database.dao.RegistroTransacaoDAO;
import org.exemplo.persistencia.database.db.ConexaoBancoHibernate;
import org.exemplo.persistencia.database.enumeration.TipoConta;
import org.exemplo.persistencia.database.enumeration.TipoTransacao;

@javax.persistence.Entity
@Table(name = "conta")
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numeroconta;
	
	@Column(name ="saldo")
	private BigDecimal saldo;
	
	@Column(name = "dataAbertura")
	private LocalDateTime dataAbertura;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "tipo_conta")
	@Enumerated(EnumType.STRING)
	private TipoConta tipoConta;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "conta")
	private List<RegistroTransacao> transacoes;
	

	
	public Conta(Integer numeroconta, BigDecimal saldo, LocalDateTime dataAbertura, Boolean status) {
		this.numeroconta = new Random().nextInt(999999999);
		this.saldo = BigDecimal.ZERO;
		saldo.setScale(4, RoundingMode.HALF_UP);
		this.dataAbertura = LocalDateTime.now();
		this.status = true;
		transacoes = new ArrayList<>();
	}


	public Conta() {
		// TODO Auto-generated constructor stub
	}


	public Integer getNumeroconta() {
		return numeroconta;
	}


	public void setNumeroconta(Integer numeroconta) {
		this.numeroconta = numeroconta;
	}


	public BigDecimal getSaldo() {
		return saldo;
	}


	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}


	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}


	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public List<RegistroTransacao> getTransacoes() {
		return transacoes;
	}


	public void setTransacoes(List<RegistroTransacao> transacoes) {
		this.transacoes = transacoes;
	}


	/**
	 * @return the tipoConta
	 */
	public TipoConta getTipoConta() {
		return tipoConta;
	}


	/**
	 * @param tipoConta the tipoConta to set
	 */
	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}


	@Override
	public int hashCode() {
		return Objects.hash(numeroconta);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(numeroconta, other.numeroconta);
	}


	
	@Override
	public String toString() {
		return "Conta [numeroconta=" + numeroconta + ", saldo=" + saldo + ", dataAbertura=" + dataAbertura + ", status="
				+ status + ", tipoConta=" + tipoConta + "]";
	}


	public void depositar(BigDecimal quantia) { 
		if (status) {
			if (quantia.compareTo(BigDecimal.ZERO) > 0) {
	            this.saldo = this.saldo.add(quantia);
	            System.out.println("Deposito realizado com sucesso!");
			} else {
				System.out.println("Valor invalido para deposito");
			}
		} else {
			System.out.println("Opera��o n�o permitida. Conta desativada");
		}
		
	}
	
	public void sacar(BigDecimal quantia) {
		if (status) {
			if (quantia.compareTo(BigDecimal.ZERO) > 0) {
				if (this.saldo.compareTo(quantia) > 0) {
					this.saldo = this.saldo.subtract(quantia);
					transacoes.add(new RegistroTransacao(quantia, TipoTransacao.DEBITO, LocalDateTime.now()));
					System.out.println("Saque realizado com sucesso!");
				} else {
					System.out.println("Saldo insuficiente");
				}
			} else {
				System.out.println("Valor invalido para saque");
			}
		} else {
			System.out.println("Opera��o n�o permitida. Conta desativada.");
		}
		
	}
	
	public void transferir(Conta c, BigDecimal quantia) {
		if (status && isStatus()) {
			if (quantia.compareTo(BigDecimal.ZERO) < 0) {
				System.out.println("Valor invalido para transferencia");
			} else if (quantia.compareTo(saldo) <= 0) {
				setSaldo(saldo.subtract(quantia));
				c.setSaldo(c.getSaldo().add(quantia));
				c.transacoes.add(new RegistroTransacao(quantia, TipoTransacao.TRANSACAO_CREDITO, LocalDateTime.now()));
				transacoes.add(new RegistroTransacao(quantia, TipoTransacao.TRANSACAO_DEBITO, LocalDateTime.now()));
			} else 
				System.out.println("Saldo insuficiente para realizar transferencia.");
			} else {
				System.out.println("Opera��o n�o pode ser realixado entre contas desativadas.");
			}
		}
	
	
}

