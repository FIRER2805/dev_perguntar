package model.vo;

import java.time.LocalDateTime;
import java.util.List;

import model.bo.TrataTexto;
import model.vo.Arvores.ArvoreRespostas;

public class Pergunta {
	private int id;
	private String titulo;
	private String conteudo;
	private LocalDateTime data;
	private LocalDateTime dataResolucao;
	private Usuario usuario;
	private Categoria categoria;
	private List<ArvoreRespostas> respostas;
	
	public Pergunta()
	{
		super();
		this.titulo = "";
		this.conteudo = "";
	}
	
	public void setDataResolucao(LocalDateTime dataResolucao) {
		this.dataResolucao = dataResolucao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		if(!titulo.isBlank())
		this.titulo = TrataTexto.formataTexto(titulo);
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		if(!conteudo.isBlank())
		this.conteudo = TrataTexto.formataTexto(conteudo);
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public LocalDateTime getDataResolucao() {
		return dataResolucao;
	}
	public void setDataResolução(LocalDateTime dataResolução) {
		this.dataResolucao = dataResolução;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public List<ArvoreRespostas> getRespostas() {
		return respostas;
	}
	public void setRespostas(List<ArvoreRespostas> respostas) {
		this.respostas = respostas;
	}
}
