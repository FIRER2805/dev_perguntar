package model.vo.Arvores;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import model.vo.Resposta;

public class ArvoreRespostas {
	NodeResposta root;

	public ArvoreRespostas() {
		this.root = null;
	}

	public ArvoreRespostas(Resposta resposta) {
		NodeResposta novaRoot = new NodeResposta(resposta);
		this.root = novaRoot;
		
	}

	public void percorreArvore() {
		if (this.root != null) {
			System.out.println(this.root.getResposta().getConteudo());
			this.percorreArvoreHelper(this.root);
		}
	}

	private void percorreArvoreHelper(NodeResposta node) {
		if (node.getFilhos() == null)
			return;
		for (NodeResposta resposta : node.getFilhos()) {
			System.out.println(resposta.getResposta().getConteudo());
			if (resposta.getFilhos() != null) {
				this.percorreArvoreHelper(resposta);
			}
		}
	}

	public NodeResposta getRoot() {
		return this.root;
	}
}
