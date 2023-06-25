package model.bo;

import model.dao.UsuarioDAO;
import model.exception.DevPerguntarException;
import model.vo.Usuario;

public class UsuarioBO {
	
	private UsuarioDAO dao = new UsuarioDAO();
	
	public int cadastrar(Usuario u) throws DevPerguntarException
	{
		int retorno = -1;
		if(validarCampos(u)) {
			retorno =  dao.inserir(u);
		}
		
		return retorno;
	}
	
	public boolean validarCampos(Usuario u) throws DevPerguntarException {
		boolean retorno = true;
		
		if(!dao.eMailDisponivel(u.geteMail())){
			retorno = false;
			throw new DevPerguntarException("Email Indisponivel");
		}
		if(u.getNome().trim().length() < 3 ) {
			retorno = false;
			throw new DevPerguntarException("Nome invalido");
		}
		
		return retorno;
		
	}
	
	
	
	public Usuario login(Usuario u)
	{
		return dao.login(u);
	}
}
