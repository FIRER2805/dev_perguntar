package model.vo.Arvores;

import java.util.ArrayList;
import java.util.List;

import model.vo.Resposta;

public class NodeResposta {
	private Resposta resposta;
	private List<NodeResposta> filhos;

	public NodeResposta(Resposta r) {
		this.resposta = r;
	}

	public NodeResposta inserir(Resposta r) {
		if (r == null)
			return null;

		if (this.filhos == null)
			this.filhos = new ArrayList<NodeResposta>();

		NodeResposta novoFilho = new NodeResposta(r);
		this.filhos.add(novoFilho);
		return novoFilho;
	}

	public Resposta getResposta() {
		return resposta;
	}

	public List<NodeResposta> getFilhos() {
		return filhos;
	}
}
