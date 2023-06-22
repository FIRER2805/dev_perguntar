package model.bo;

import java.util.ArrayList;

import model.dao.PerguntaDAO;
import model.vo.Pergunta;

public class PerguntaBO {
	private PerguntaDAO dao = new PerguntaDAO();
	
	public ArrayList<Pergunta> busca()
	{
		return dao.busca();
	}
}
