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

public class RespostaDAO {
	
	public int inserir(Resposta r)
	{
		int chave = 0;
		ResultSet chaves = null;
		String sql = "insert into resposta(conteudo, id_pergunta, solucao) values(?, ?, ?);";
		Connection c = Banco.getConnection();
		PreparedStatement ps = Banco.getPreparedStmtPK(c, sql);
		try {
			ps.setString(1, r.getConteudo());
			ps.setInt(2, r.getIdPergunta());
			ps.setBoolean(3, r.isSolucao());
			
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
	
	public ArrayList<Resposta> buscarTodos(int idPergunta)
	{
		String sql =  "select * from resposta where id_pergunta = ?";
		ArrayList<Resposta> respostas = new ArrayList<Resposta>();
		Connection conn = Banco.getConnection();
		PreparedStatement pStmt = Banco.getPreparedStmt(conn, sql);
		ResultSet rs = null;
		try {
			pStmt.setInt(1, idPergunta);
			rs = pStmt.executeQuery();
			while(rs.next())
			{
				Resposta r = new Resposta();
				r.setId(rs.getInt("id"));
				r.setConteudo(rs.getString("conteudo"));
				r.setIdPergunta(rs.getInt("id_pergunta"));
				r.setSolucao(rs.getBoolean("solucao"));
				respostas.add(r);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro no método buscarTodos da classe RespostaDAO");
			System.out.println(e.getMessage());
		}
		finally 
		{
			Banco.closeResultSet(rs);
			Banco.closeStatement(pStmt);
			Banco.closeConnection(conn);
		}
		return respostas;
	}
	
	public Resposta consultarPorId(int idResposta)
	{
		String sql =  "select * from resposta where id = ?";
		Resposta r = null;
		Connection conn = Banco.getConnection();
		PreparedStatement pStmt = Banco.getPreparedStmt(conn, sql);
		ResultSet rs = null;
		try {
			pStmt.setInt(1, idResposta);
			rs = pStmt.executeQuery();
			while(rs.next())
			{
				r = construirDoResultSet(rs);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro no método buscarPorId da classe RespostaDAO");
			System.out.println(e.getMessage());
		}
		finally 
		{
			Banco.closeResultSet(rs);
			Banco.closeStatement(pStmt);
			Banco.closeConnection(conn);
		}
		return r;
	}
	
	private Resposta construirDoResultSet(ResultSet rs) throws SQLException {
		Resposta r = new Resposta();
		r.setId(rs.getInt("id"));
		r.setConteudo(rs.getString("conteudo"));
		r.setIdPergunta(rs.getInt("id_pergunta"));
		r.setSolucao(rs.getBoolean("solucao"));
		
		int idRespostaPai = rs.getInt("id_resposta");
		
		if(idRespostaPai > 0) {
			r.setRespostaPai(consultarPorId(idRespostaPai));
		}
		
		return r;
	}

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
