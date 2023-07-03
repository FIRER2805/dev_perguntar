package model.bo;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

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

	public List<DefaultMutableTreeNode> consultarPorIdPergunta(int idPergunta) {
		return respostaDAO.montaArvoresResposta(idPergunta);
	}
}
