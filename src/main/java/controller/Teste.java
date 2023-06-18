package controller;

import model.dao.UsuarioDAO;
import model.vo.Usuario;

public class Teste {
	public static void main(String[] args)
	{
		
		Usuario u = new Usuario();
		UsuarioDAO uDAO = new UsuarioDAO();
//		
//		u.setNome("Gabriel");
//		u.seteMail("gabriel_henrique2805@hotmail.com");
//		u.setSenha("Lasanha2805g");
//		
//		System.out.println(uDAO.inserir(u));
//		
//		System.out.println(uDAO.buscarPorId(1));
		
		System.out.println(uDAO.eMailDisponivel("gabriel_henrique2805@hotmail.com"));
	}
}
