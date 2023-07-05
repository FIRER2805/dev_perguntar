package controller;

import java.util.ArrayList;

import model.bo.UsuarioBO;
import model.exception.DevPerguntarException;
import model.gerador.GeradorPlanilha;
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

	public ArrayList<Usuario> pesquisarUsuario(PesquisaUsuario pesquisaUsuario) {

		return bo.pesquisarUsuario(pesquisaUsuario);
	}

	public String gerarPlanilha(ArrayList<Usuario> usuarios, String caminhoEscolhido) throws DevPerguntarException {
		if(usuarios == null || caminhoEscolhido == null || caminhoEscolhido.trim().isEmpty()) {
			throw new DevPerguntarException("Preencha todos os campos");
		}
		
		GeradorPlanilha gerador = new GeradorPlanilha();
		return gerador.gerarPlanilhaUsuarios(usuarios, caminhoEscolhido);
	}
}
