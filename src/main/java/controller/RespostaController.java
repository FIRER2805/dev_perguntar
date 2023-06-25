package controller;

import model.bo.RespostaBO;
import model.vo.Resposta;

public class RespostaController {
	private RespostaBO respostaBO = new RespostaBO();
	
	public int inserir(Resposta resposta)
	{
		return respostaBO.inserir(resposta);
	}
}
