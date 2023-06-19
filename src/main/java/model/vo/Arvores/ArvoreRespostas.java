package model.vo.Arvores;

import model.vo.Resposta;

public class ArvoreRespostas {
	NodeResposta root;

	public ArvoreRespostas(Resposta resposta) {
		NodeResposta novaRoot = new NodeResposta();
		novaRoot.setResposta(resposta);
		this.root = novaRoot;
	}

	public void percorreArvore() {
		if (this.root != null) {
			this.percorreArvoreHelper(this.root);
		}
	}

	private void percorreArvoreHelper(NodeResposta node) {
		if (node.getRespostaFilhos() != null) {
			for (NodeResposta resposta : node.getRespostaFilhos()) {
				if (resposta.getRespostaFilhos() != null) {
					this.percorreArvoreHelper(resposta);
				}
				System.out.println(resposta.getResposta().getId());
			}
		}
	}
}
