package controller;

import java.util.ArrayList;

import model.bo.UsuarioBO;
import model.exception.DevPerguntarException;
import model.vo.Pergunta;
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

	public boolean editarUsuario(Usuario userEditado) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean excluirUsuario(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
