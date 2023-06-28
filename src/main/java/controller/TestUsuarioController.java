package controller;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;
import model.exception.DevPerguntarException;
import model.vo.Usuario;

public class TestUsuarioController extends TestCase {
	private UsuarioController controller = new UsuarioController();
	public void testeCadastro() throws DevPerguntarException
	{
		Usuario valorPassado = new Usuario();
		valorPassado.seteMail("exemplo@hotmail.com");
		valorPassado.setSenha("exemplo123");
		
		Usuario retornoEsperado = new Usuario();
		retornoEsperado.setId(1);;
		retornoEsperado.setNome("Gabriel");
		retornoEsperado.seteMail("gabriel.medeiros3@alunos.sc.senac.br");
		retornoEsperado.setSenha("123456");
		
		Usuario retornoFeito = controller.login(valorPassado);
		
		assertEquals(retornoEsperado.getId(), retornoFeito.getId());
		assertEquals(retornoEsperado.getNome(), retornoFeito.getNome());
		assertEquals(retornoEsperado.geteMail(), retornoFeito.geteMail());
		assertEquals(retornoEsperado.getSenha(), retornoFeito.getSenha());
	}
}
