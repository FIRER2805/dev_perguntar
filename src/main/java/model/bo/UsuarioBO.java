package model.bo;

import java.util.ArrayList;

import model.dao.UsuarioDAO;
import model.exception.DevPerguntarException;
import model.vo.PesquisaUsuario;
import model.vo.Usuario;

public class UsuarioBO {
	
	private UsuarioDAO dao = new UsuarioDAO();
	
	public int cadastrar(Usuario u) throws DevPerguntarException
	{
		this.validarCampos(u);
		return dao.inserir(u);
	}
	
	private void validarCampos(Usuario u) throws DevPerguntarException {
		if(!dao.eMailDisponivel(u.geteMail())){
			throw new DevPerguntarException("Email Indisponivel");
		}
		if(u.getNome().trim().length() < 3 ) {
			throw new DevPerguntarException("Nome invalido");
		}
	}
	
	// outra ideia é verificar no banco se mudou o email 
	// e colocar esta confimação no metodo acima tendo assim apenas um método
	private void validarCampos(Usuario u, String emailAntigo) throws DevPerguntarException {
		if(!dao.eMailDisponivel(u.geteMail()) && !u.geteMail().equals(emailAntigo)){
			throw new DevPerguntarException("Email Indisponivel");
		}
		if(u.getNome().trim().length() < 3 ) {
			throw new DevPerguntarException("Nome invalido");
		}
	}
	
	public Usuario login(Usuario u)
	{
		return dao.login(u);
	}
	
	public int atualizar(Usuario u, String emailAntigo) throws DevPerguntarException
	{
		validarCampos(u,emailAntigo);
		return dao.atualizar(u);
	}
	
	public int excluir(Usuario u)
	{
		return dao.excluir(u.getId());
	}

	public boolean pesquisarUsuario(ArrayList<Usuario> usuarios, PesquisaUsuario pesquisaUsuario) {

		return dao.pesquisarUsuario(usuarios, pesquisaUsuario);
	}
}
