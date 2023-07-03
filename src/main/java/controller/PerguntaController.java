package controller;

import java.util.ArrayList;

import model.bo.PerguntaBO;
import model.exception.DevPerguntarException;
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
}
