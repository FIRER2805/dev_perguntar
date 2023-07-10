package model.vo;

public class Resposta {
	private int id;
	private Resposta respostaPai;
	private String conteudo;
	private int idPergunta;
	private boolean solucao;

	public Resposta getRespostaPai() {
		return respostaPai;
	}

	public void setRespostaPai(Resposta respostaPai) {
		this.respostaPai = respostaPai;
	}

	public boolean isSolucao() {
		return solucao;
	}

	public void setSolucao(boolean solucao) {
		this.solucao = solucao;
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

	@Override
	public String toString() {
		String retorno = "";
		if (solucao) {
			retorno = "(Solução)";
		}
		retorno = retorno + "Resposta: " + this.getConteudo();
		return retorno;
	}
}
