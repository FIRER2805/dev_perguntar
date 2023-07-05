package controller;

import java.util.ArrayList;

import model.bo.PerguntaBO;
import model.exception.DevPerguntarException;
import model.gerador.GeradorPlanilha;
import model.vo.Pergunta;

public class PerguntaController {
	private PerguntaBO bo = new PerguntaBO();
	
	public ArrayList<Pergunta> busca()
	{
		return bo.busca();
	}
	
	public ArrayList<Pergunta> busca(String texto, String categoria, boolean resolvido)
	{
		return bo.busca(texto, categoria, resolvido);
	}
	
	public ArrayList<Pergunta> busca(int idUsuario)
	{
		return bo.busca(idUsuario);
	}
	
	public int perguntar(Pergunta pergunta) throws DevPerguntarException
	{
		return bo.perguntar(pergunta);
	}

	public String gerarPlanilha(ArrayList<Pergunta> perguntas, String caminhoEscolhido) throws DevPerguntarException {
		
		if(perguntas == null || caminhoEscolhido == null || caminhoEscolhido.trim().isEmpty()) {
			throw new DevPerguntarException("Preencha todos os campos");
		}
		
		GeradorPlanilha gerador = new GeradorPlanilha();
		return gerador.gerarPlanilhaPerguntas(perguntas, caminhoEscolhido);
	}

	public int atualizar(Pergunta pergunta)
	{
		return bo.atualizar(pergunta);
	}
	
	public int excluir(Pergunta pergunta) {
		return bo.excluir(pergunta);
	}



















}
