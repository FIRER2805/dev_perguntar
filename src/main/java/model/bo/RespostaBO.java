package model.bo;

import model.dao.RespostaDAO;
import model.exception.DevPerguntarException;
import model.vo.Resposta;

public class RespostaBO {
	private RespostaDAO respostaDAO = new RespostaDAO();
	
	public int inserir(Resposta resposta) throws DevPerguntarException
	{
		if(resposta.getConteudo().trim().isEmpty())
			throw new DevPerguntarException("a respota deve conter pelo menos 1 caractere");
		return  respostaDAO.inserir(resposta);
	}
}