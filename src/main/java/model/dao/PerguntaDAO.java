package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Banco;
import model.vo.Pergunta;

public class PerguntaDAO {
	
//	private int id;
//	private String titulo;
//	private String conteudo;
//	private LocalDateTime data;
//	private LocalDateTime dataResolucao;
//	private Usuario usuario;
//	private Categoria categoria;
	
	public int inserir(Pergunta p)
	{
		int chave = 0;
		ResultSet chaves = null;
		String sql = "insert into pergunta(titulo, conteudo, data_pergunta, data_resolucao,id_usuario, id_categoria) values(?,?,now(),?,?,?);";
		Connection c = Banco.getConnection();
		PreparedStatement ps = Banco.getPreparedStmtPK(c, sql);
		try {
			ps.setString(1, p.getTitulo());
			ps.setString(2, p.getTitulo());
			ps.setString(3, p.getDataResolucao().toString());
			ps.setInt(4, p.getUsuario().getId());
			ps.setInt(5, p.getCategoria().getId());
			
			ps.executeUpdate();
			chaves = ps.getGeneratedKeys();
			
			if(chaves.next())
			{
				chave = chaves.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			Banco.closeResultSet(chaves);
			Banco.closeStatement(ps);
			Banco.closeConnection(c);
		}
		return chave;
	}
	
//	public Pergunta buscarPorId(int id)
//	{
//		String sql =  "select * from categoria where id = ?";
//		Categoria categoria = null;
//		Connection conn = Banco.getConnection();
//		PreparedStatement pStmt = Banco.getPreparedStmt(conn, sql);
//		ResultSet rs = null;
//		try {
//			rs = pStmt.executeQuery();
//			if(rs.next())
//			{
//				categoria = new Categoria();
//				categoria.setId(rs.getInt(1));
//				categoria.setNome(rs.getString(2));
//			}
//		}
//		catch(SQLException e)
//		{
//			categoria = null;
//			System.out.println("Erro no método buscarPorId da classe CategoriaDAO");
//			System.out.println(e.getMessage());
//		}
//		finally 
//		{
//			Banco.closeResultSet(rs);
//			Banco.closeStatement(pStmt);
//			Banco.closeConnection(conn);
//		}
//		return categoria;
//	}
	
	public int atualizar(Pergunta p)
	{
		String sql =  "update pergunta set titulo = ?, conteudo = ? where id = ?";
		int registrosAfetados = 0;
		Connection conn = Banco.getConnection();
		PreparedStatement pStmt = Banco.getPreparedStmt(conn, sql);
		try {
			pStmt.setString(1, p.getTitulo());
			pStmt.setString(2, p.getConteudo());
			pStmt.setInt(3, p.getId());
			registrosAfetados = pStmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("Erro no método atualizar da classe PerguntaDAO");
			System.out.println(e.getMessage());
		}
		finally 
		{
			Banco.closeStatement(pStmt);
			Banco.closeConnection(conn);
		}
		return registrosAfetados;
	}
	
	public int excluir(int id)
	{
		String sql =  "delete from pergunta where id = ?";
		int registrosAfetados = 0;
		Connection conn = Banco.getConnection();
		PreparedStatement pStmt = Banco.getPreparedStmt(conn, sql);
		try {
			pStmt.setInt(1, id);
			registrosAfetados = pStmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("Erro no método excluir da classe PerguntaDAO");
			System.out.println(e.getMessage());
		}
		finally 
		{
			Banco.closeStatement(pStmt);
			Banco.closeConnection(conn);
		}
		return registrosAfetados;
	}
}
