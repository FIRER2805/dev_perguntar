package controller;

import model.bo.UsuarioBO;
import model.vo.Usuario;

public class UsuarioController {
	private UsuarioBO bo = new UsuarioBO();
	
	public int cadastrar(Usuario u)
	{
		return bo.cadastrar(u);
	}
	
	public Usuario login(Usuario u)
	{
		return bo.login(u);
	}
}
