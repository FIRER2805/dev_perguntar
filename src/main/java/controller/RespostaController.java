package controller;

import model.bo.RespostaBO;
import model.exception.DevPerguntarException;
import model.vo.Resposta;

public class RespostaController {
	private RespostaBO respostaBO = new RespostaBO();
	
	public int inserir(Resposta resposta) throws DevPerguntarException
	{
		return respostaBO.inserir(resposta);
	}
}
