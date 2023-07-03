package controller;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

public class Teste {

	public static void main(String[] args) {
		RespostaController controller = new RespostaController();
		ArrayList<DefaultMutableTreeNode> arvoresRespostas = (ArrayList<DefaultMutableTreeNode>)controller.consultarPorIdPergunta(1);
		System.out.println(arvoresRespostas.toString());
	}
}
