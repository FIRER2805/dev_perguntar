package model.vo;

import model.bo.TrataTexto;

public class Categoria {
	private int id;
	private String nome; 
	
	public Categoria() {
		super();
		nome = "";
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
	
	@Override
	public String toString()
	{
		return nome;
	}
}
