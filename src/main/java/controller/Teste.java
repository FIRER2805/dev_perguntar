package controller;

import java.util.ArrayList;

import model.dao.CategoriaDAO;
import model.dao.PerguntaDAO;
import model.vo.Categoria;
import model.vo.Pergunta;
import model.vo.Resposta;

public class Teste {
	public static void main(String[] args)
	{
		
		PerguntaDAO dao = new PerguntaDAO();
		
		CategoriaDAO daoc = new CategoriaDAO();
		
		ArrayList<Categoria> categorias = daoc.buscarTodas();
		for(Categoria categoria : categorias)
		{
			System.out.println(categoria.getId());
			System.out.println(categoria.getNome());
		}
			
		
		
		ArrayList<Pergunta> perguntas = dao.busca();
		
		for(Pergunta pergunta: perguntas)
		{
			System.out.println(pergunta.getCategoria().getNome());
			System.out.println(pergunta.getUsuario().getNome());
		}
		
//		Resposta resposta = new Resposta();
//		resposta.setConteudo("teste 1");
//		ArvoreRespostas arvore = new ArvoreRespostas(resposta);
//		NodeResposta root = arvore.getRoot();
//		Resposta resposta2 = new Resposta();
//		resposta2.setConteudo("teste 2");
//		root.inserir(resposta2);
//		NodeResposta resposta3 = new NodeResposta(geraResposta("teste3"));
//		root.getFilhos().add(resposta3);
//		NodeResposta resposta4 = new NodeResposta(geraResposta("teste4"));
//		root.getFilhos().add(resposta4);
//		resposta3.inserir(geraResposta("teste5"));
//		resposta4.inserir(geraResposta("teste6"));
//		arvore.percorreArvore();
	}
	
	public static Resposta geraResposta(String r)
	{
		Resposta resposta = new Resposta();
		resposta.setConteudo(r);
		return resposta;
	}
}
