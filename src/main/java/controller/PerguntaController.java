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
}
