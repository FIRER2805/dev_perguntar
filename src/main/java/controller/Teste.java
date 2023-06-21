package controller;

import model.vo.Resposta;
import model.vo.Arvores.ArvoreRespostas;
import model.vo.Arvores.NodeResposta;

public class Teste {
	public static void main(String[] args)
	{
		Resposta resposta = new Resposta();
		resposta.setConteudo("teste 1");
		ArvoreRespostas arvore = new ArvoreRespostas(resposta);
		NodeResposta root = arvore.getRoot();
		Resposta resposta2 = new Resposta();
		resposta2.setConteudo("teste 2");
		root.inserir(resposta2);
		NodeResposta resposta3 = new NodeResposta(geraResposta("teste3"));
		root.getFilhos().add(resposta3);
		NodeResposta resposta4 = new NodeResposta(geraResposta("teste4"));
		root.getFilhos().add(resposta4);
		resposta3.inserir(geraResposta("teste5"));
		resposta4.inserir(geraResposta("teste6"));
		arvore.percorreArvore();
	}
	
	public static Resposta geraResposta(String r)
	{
		Resposta resposta = new Resposta();
		resposta.setConteudo(r);
		return resposta;
	}
}
