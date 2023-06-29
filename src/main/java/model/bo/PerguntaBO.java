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
	
	public ArrayList<Pergunta> busca(String texto, String categoria, boolean resolvido)
	{
		return dao.busca(texto, categoria, resolvido);
	}

	public int perguntar(Pergunta pergunta) {
		if(pergunta.getTitulo().trim().isEmpty())
			// erro: O titulo deve conter pelo menos um caractere
			return -1;
		if(pergunta.getConteudo().trim().isEmpty())
			// erro: O conteudo deve conter pelo menos um caractere
			return -1;
		if(pergunta.getConteudo().length() > 1000)
			// erro: O conteudo deve conter pelo menos um caractere
			return -1;
		
		return dao.inserir(pergunta);
	}

	public ArrayList<Pergunta> busca(int idUsuario) {
		return dao.busca(idUsuario);
	}
}
