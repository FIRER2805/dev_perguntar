package model.vo;

import java.time.LocalDateTime;

public class PesquisaUsuario {

	private String Busca;
	private boolean temPergunta;
	private boolean temresposta;
	private boolean temSolucao;
	private String campoOrdem;
	private String ordemPesquisa;
	

	public PesquisaUsuario() {
		super();
	}

	
	
	public String getCampoOrdem() {
		return campoOrdem;
	}

	public void setCampoOrdem(String campoOrdem) {
		this.campoOrdem = campoOrdem;
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

	public boolean temFiltros()
	{
		if(this.isTemPergunta())
			return true;
		if(this.isTemresposta())
			return true;
		if(this.isTemSolucao())
			return true;
		
		return false;
	}

	public String criaFiltro()
	{
		String retorno = "having ";
		boolean primeiro = true;
		
		if(this.isTemPergunta())
		{
			if(!primeiro)
				retorno += "and ";
			retorno += "(select count(p.id) from pergunta p where p.id_usuario = u.id) > 0 ";
			primeiro = false;
		}
		
		if(this.isTemresposta())
		{
			if(!primeiro)
				retorno += "and ";
			retorno += "(select count(r.id) from resposta r where r.id_usuario = u.id) > 0 ";
			primeiro = false;
		}
		
		if(this.isTemSolucao())
		{
			if(!primeiro)
				retorno += "and ";
			retorno += "(select count(r.id) from resposta r where r.id_usuario = u.id and r.solucao = 1) > 0 ";
			primeiro = false;
		}
		
		return retorno;
	}
	
	public String ordem()
	{
		String retorno = "";
		String ordem = "";
		String campo = "";
		if(this.campoOrdem.equals("Nome"))
			campo = "u." + this.campoOrdem;
		if(this.campoOrdem.equals("Resposta"))
			campo = "qtd_respostas";
		if(this.campoOrdem.equals("Pergunta"))
			campo = "qntd_perguntas";
		if(this.campoOrdem.equals("Solucao"))
			campo = "resposta_solucao";
		if(this.ordemPesquisa.equals("Decrescente"))
			ordem = " desc";
		retorno = "order by " + campo + ordem;
		return retorno;
	}

}
