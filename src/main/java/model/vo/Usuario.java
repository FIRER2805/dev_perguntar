package model.vo;

import model.bo.TrataTexto;

public class Usuario {
	private int id;
	private String nome;
	private String eMail;
	private String senha;
	private boolean ativo;
	private int numPergunta;
	private int numResposta;
	private int numsolucao;
	
	public Usuario() {
		super();
		this.id = 0;
		this.nome = "";
		this.eMail = "";
		this.senha = "";
	}
	
	
	public Usuario(int id, String nome, String eMail, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.eMail = eMail;
		this.senha = senha;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if(!nome.isBlank())
		this.nome = TrataTexto.formataTexto(nome);
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public int getNumPergunta() {
		return numPergunta;
	}


	public void setNumPergunta(int numPergunta) {
		this.numPergunta = numPergunta;
	}


	public int getNumResposta() {
		return numResposta;
	}


	public void setNumResposta(int numResposta) {
		this.numResposta = numResposta;
	}


	public int getNumsolucao() {
		return numsolucao;
	}


	public void setNumsolucao(int numsolucao) {
		this.numsolucao = numsolucao;
	}


	@Override
	public String toString()
	{
		return nome;
	}
	
}
