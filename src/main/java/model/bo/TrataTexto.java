package model.bo;

public class TrataTexto {
	public static String formataTexto(String texto) 
	{
		char primeiraLetra = Character.toUpperCase(texto.charAt(0));
		String resto = texto.substring(1, texto.length()).toLowerCase();
		return primeiraLetra + resto;
	}
}
