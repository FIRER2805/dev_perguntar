package model.bo;

import model.dao.RespostaDAO;
import model.vo.Resposta;

public class RespostaBO {
	private RespostaDAO respostaDAO = new RespostaDAO();
	
	public int inserir(Resposta resposta)
	{
		return  respostaDAO.inserir(resposta);
	}
}
