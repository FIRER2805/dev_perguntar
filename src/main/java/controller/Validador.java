package controller;

public abstract class Validador {
	public static boolean validaEmail(String email)
	{
		return email.matches("[a-zA-Z0-9._]{1,}[@][a-z]{1,}[.][a-z.]{1,}") && email.charAt(email.length() - 1) != '.';
	}
	
	public static boolean validaSenha(String senha)
	{
		return senha.matches("[a-zA-Z0-9]{1,}");
	}
	
	public static boolean validarNome(String nome)
	{
		return nome.matches("[a-zA-Z0-9]{1,}");
	}
}