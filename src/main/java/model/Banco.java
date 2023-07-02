package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Banco {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String NOME_BANCO = "db_dev_perguntar";
	public static final String USUARIO = "root";
	public static final String SENHA = "Lasanha2805g";
	public static final String URL = "jdbc:mysql://localhost:3306/" + NOME_BANCO;
	
	
	public static Connection getConnection()
	{
		Connection retorno = null;
		try 
		{
			Class.forName(DRIVER);
			retorno = DriverManager.getConnection(URL,USUARIO,SENHA);	
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao buscar conexao com o banco");
		} catch (SQLException e) {
			System.out.println("Erro ao buscar conexao com o banco");
		}
		return retorno;
	}
	
	
	public static Statement getStatement(Connection c) 
	{
		Statement stmt = null;
		try {
			stmt = c.createStatement();
		} catch (SQLException e) {
			stmt = null;
			System.out.println("Erro no getStatement na classe Banco");
		}
		return stmt;
	}
	
	public static PreparedStatement getPreparedStmt(Connection c, String s)
	{
		PreparedStatement pStmt = null;
		try 
		{
			pStmt = c.prepareStatement(s);
		}
		catch(SQLException e)
		{
			pStmt = null;
			System.out.println("Erro no getPreparedStmt na classe Banco");
		}
		return pStmt;
	}
	
	public static PreparedStatement getPreparedStmtPK(Connection c, String s)
	{
		PreparedStatement pStmt = null;
		try 
		{
			pStmt = c.prepareStatement(s, PreparedStatement.RETURN_GENERATED_KEYS);
		}
		catch(SQLException e)
		{
			pStmt = null;
			System.out.println("Erro no getPreparedStmtPK na classe Banco");
		}
		return pStmt;
	}
	
	public static void closeConnection(Connection c)
	{
		try {
			c.close();
		} catch (SQLException e) {
			System.out.println("Erro no closeConnection na classe Banco");
		}
	}
	
	public static void closeStatement(Statement s)
	{
		try {
			s.close();
		} catch (SQLException e) {
			System.out.println("Erro no closeStatement na classe Banco");
		}
	}
	
	public static void closeResultSet(ResultSet r)
	{
		try {
			r.close();
		} catch (SQLException e) {
			System.out.println("Erro no closeResultSet na classe Banco");
		}
	}
}
