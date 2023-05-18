package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
	public static final String NOME_BANCO = "db_dev_perguntar";
	public static final String USUARIO = "root";
	public static final String SENHA = "admin";
	public static final String URL = "jdbc:mysql://localhost:3306/" + NOME_BANCO;
	
	public static Connection getConnection()
	{
		Connection retorno = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			retorno = DriverManager.getConnection(URL,USUARIO,SENHA);	
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao buscar conexao com o banco");
		} catch (SQLException e) {
			System.out.println("Erro ao buscar conexao com o banco");
		}
		return retorno;
	}
}
