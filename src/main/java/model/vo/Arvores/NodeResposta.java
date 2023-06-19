package model.vo.Arvores;

import java.util.ArrayList;

import model.vo.Resposta;

public class NodeResposta {
	private Resposta resposta;
	private ArrayList<NodeResposta> respostaFilhos;
	
	public void inserir(Resposta r)
	{
		if(r != null)
		{
			this.respostaFilhos = new ArrayList<NodeResposta>();
			NodeResposta novoFilho = new NodeResposta();
			novoFilho.setResposta(r);
			respostaFilhos.add(novoFilho);
		}
	}

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	public ArrayList<NodeResposta> getRespostaFilhos() {
		return respostaFilhos;
	}

	public void setRespostaFilhos(ArrayList<NodeResposta> respostaFilhos) {
		this.respostaFilhos = respostaFilhos;
	}
}
