package controller;

import junit.framework.TestCase;
import model.exception.DevPerguntarException;
import model.vo.Resposta;

public class TestRespostaController extends TestCase {
	RespostaController controller = new RespostaController();
	public void testInserir() throws DevPerguntarException
	{
		int retornoEsperado = 1;
		
		Resposta valorPassado = new Resposta();
		valorPassado.setConteudo("Java é uma linguagem de programação muito popular,"
				+ " ele tem vário recursos como jdk e jvm, para saber mais entre neste"
				+ " link: https://dev.java/learn/getting-started/”");
		valorPassado.setSolucao(false);
		valorPassado.setIdPergunta(1);
		
		int retornoFeito = controller.inserir(valorPassado);
		
		assertEquals(retornoEsperado, retornoFeito);
	}
}
