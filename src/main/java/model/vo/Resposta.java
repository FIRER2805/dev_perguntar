package model.vo;

public class Resposta {
	private int id;
	private String conteudo;
	private int idPergunta;
	private Usuario usuario;
	private int idRespostaPai;
	private Resposta respostaPai;
	
	public int getIdRespostaPai() {
		return idRespostaPai;
	}
	public void setIdRespostaPai(int idRespostaPai) {
		this.idRespostaPai = idRespostaPai;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public int getIdPergunta() {
		return idPergunta;
	}
	public void setIdPergunta(int idPergunta) {
		this.idPergunta = idPergunta;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Resposta getRespostaPai() {
		return respostaPai;
	}
	public void setRespostaPai(Resposta resposta) {
		this.respostaPai = resposta;
	}
}
