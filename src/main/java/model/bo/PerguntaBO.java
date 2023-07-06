package model.bo;

import java.util.ArrayList;

import model.dao.PerguntaDAO;
import model.exception.DevPerguntarException;
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

	public int perguntar(Pergunta pergunta) throws DevPerguntarException {
		String erro = "";
		
		if(pergunta.getTitulo().trim().isEmpty())
			erro += "O titulo precisa ter pelo menos 1 caractere";
		if(pergunta.getConteudo().trim().isEmpty())
			erro += "O conteudo precisa ter pelo menos 1 caractere\n";
		if(pergunta.getConteudo().length() > 1000)
			erro += "O coneteudo não pode ter mais de 1000 caracteres\n";
		
		if(!erro.trim().isEmpty())
			throw new DevPerguntarException(erro);
		
		return dao.inserir(pergunta);
	}

	public ArrayList<Pergunta> busca(int idUsuario) {
		return dao.busca(idUsuario);
	}
	
	public int excluir(Pergunta pergunta)
	{
		return dao.excluir(pergunta.getId());
	}

	public int atualizar(Pergunta pergunta) {
		return dao.atualizar(pergunta);
	}
	
}
