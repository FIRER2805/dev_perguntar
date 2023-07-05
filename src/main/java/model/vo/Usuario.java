package model.vo;

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
		this.nome = nome;
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
	
	@Override
	public String toString()
	{
		return nome;
	}
	
}
