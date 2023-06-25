package model.vo;

import java.time.LocalDateTime;
import java.util.List;

import model.vo.Arvores.ArvoreRespostas;

public class Pesquisa {
	
	private boolean resolvido;
	private Categoria categoria;
	private String duvida;
	private LocalDateTime dataInicial;
	private LocalDateTime dataFinal;
	public boolean isResolvido() {
		return resolvido;
	}
	public void setResolvido(boolean resolvido) {
		this.resolvido = resolvido;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getDuvida() {
		return duvida;
	}
	public void setDuvida(String duvida) {
		this.duvida = duvida;
	}
	public LocalDateTime getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(LocalDateTime dataInicial) {
		this.dataInicial = dataInicial;
	}
	public LocalDateTime getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(LocalDateTime dataFinal) {
		this.dataFinal = dataFinal;
	}

}
