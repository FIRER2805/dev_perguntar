package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Banco;
import model.vo.Categoria;
import model.vo.Pergunta;
import model.vo.Resposta;

public class RepostaDAO {
	
	public int inserir(Resposta r)
	{
		int chave = 0;
		ResultSet chaves = null;
		String sql = "insert into resposta(conteudo, id_pergunta, id_usuario) values(?, ?, ?);";
		Connection c = Banco.getConnection();
		PreparedStatement ps = Banco.getPreparedStmtPK(c, sql);
		try {
			ps.setString(1, r.getConteudo());
			ps.setInt(2, r.getIdPergunta());
			ps.setInt(3, r.getUsuario().getId());
			
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
	
//	public ArrayList<Resposta> buscarTodos(int idPergunta)
//	{
//		UsuarioDAO usuarioDAO = new UsuarioDAO();
//		String sql =  "select * from resposta where id_pergunta = ?";
//		ArrayList<Resposta> respostas = new ArrayList<Resposta>();
//		Connection conn = Banco.getConnection();
//		PreparedStatement pStmt = Banco.getPreparedStmt(conn, sql);
//		ResultSet rs = null;
//		try {
//			rs = pStmt.executeQuery();
//			while(rs.next())
//			{
//				Resposta r = new Resposta();
//				r.setId(rs.getInt(1));
//				r.setConteudo(rs.getString(2));
//				r.setIdPergunta(rs.getInt(3));
//				r.setUsuario(usuarioDAO.buscarPorId(rs.getInt(4)));
//				r.
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
