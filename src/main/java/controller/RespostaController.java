package controller;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import model.bo.RespostaBO;
import model.exception.DevPerguntarException;
import model.vo.Resposta;

public class RespostaController {
	private RespostaBO respostaBO = new RespostaBO();
	
	public int inserir(Resposta resposta) throws DevPerguntarException
	{
		return respostaBO.inserir(resposta);
	}
	
	public List<DefaultMutableTreeNode> consultarPorIdPergunta(int idPergunta)
	{
		return respostaBO.consultarPorIdPergunta(idPergunta);
	}
}
