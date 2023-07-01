package org.exemplo.persistencia.database.enumeration;

public enum TipoConta {

	 CORRENTE("Corrente"),
	 POUPANCA("Poupança");
	
	private final String descricao;
	
	TipoConta(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
