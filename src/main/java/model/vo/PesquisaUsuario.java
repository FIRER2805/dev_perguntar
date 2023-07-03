package model.vo;

import java.time.LocalDateTime;

public class PesquisaUsuario {

	private String Busca;
	private boolean temPergunta;
	private boolean temresposta;
	private boolean temSolucao;
	private String ordemPesquisa;
	

	public PesquisaUsuario() {
		super();
	}

	public String getBusca() {
		return Busca;
	}

	public void setBusca(String busca) {
		Busca = busca;
	}

	public boolean isTemPergunta() {
		return temPergunta;
	}

	public void setTemPergunta(boolean temPergunta) {
		this.temPergunta = temPergunta;
	}

	public boolean isTemresposta() {
		return temresposta;
	}

	public void setTemresposta(boolean temresposta) {
		this.temresposta = temresposta;
	}

	public boolean isTemSolucao() {
		return temSolucao;
	}

	public void setTemSolucao(boolean temSolucao) {
		this.temSolucao = temSolucao;
	}

	public String getOrdemPesquisa() {
		return ordemPesquisa;
	}

	public void setOrdemPesquisa(String ordemPesquisa) {
		this.ordemPesquisa = ordemPesquisa;
	}

	

	

}
