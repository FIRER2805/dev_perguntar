package model.bo;

import model.dao.UsuarioDAO;
import model.vo.Usuario;

public class UsuarioBO {
	
	private UsuarioDAO dao = new UsuarioDAO();
	
	public int cadastrar(Usuario u)
	{
		if(!dao.eMailDisponivel(u.geteMail()) || u.getNome().trim().length() < 3)
		{
			// joga exeção
		}
		return dao.inserir(u);
	}
	
	public Usuario login(Usuario u)
	{
		return dao.login(u);
	}
}
