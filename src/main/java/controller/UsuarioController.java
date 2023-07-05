package controller;

import java.util.ArrayList;

import model.bo.UsuarioBO;
import model.exception.DevPerguntarException;
import model.vo.Pergunta;
import model.vo.PesquisaUsuario;
import model.vo.Usuario;

public class UsuarioController {
	private UsuarioBO bo = new UsuarioBO();
	
	public int cadastrar(Usuario u) throws DevPerguntarException
	{
		return bo.cadastrar(u);
	}
	
	public Usuario login(Usuario u)
	{
		return bo.login(u);
	}

	public int editarUsuario(Usuario userEditado, String emailAntigo) throws DevPerguntarException {
		return bo.atualizar(userEditado, emailAntigo);
	}

	public int excluirUsuario(Usuario u) {
		
		
		return bo.excluir(u);
	}

	public boolean pesquisarUsuario(ArrayList<Usuario> usuarios, PesquisaUsuario pesquisaUsuario) {

		return bo.pesquisarUsuario(pesquisaUsuario);
	}
}
