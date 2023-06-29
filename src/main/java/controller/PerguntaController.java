package controller;

import java.util.ArrayList;

import model.bo.PerguntaBO;
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
	
	public int perguntar(Pergunta pergunta)
	{
		return bo.perguntar(pergunta);
	}
}
