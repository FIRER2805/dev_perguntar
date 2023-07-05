package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.Banco;
import model.vo.Categoria;
import model.vo.Pergunta;
import model.vo.Usuario;

public class PerguntaDAO {
	
	public int inserir(Pergunta p)
	{
		int chave = 0;
		ResultSet chaves = null;
		String sql = "insert into pergunta(titulo, conteudo, data_pergunta, id_usuario, id_categoria) values(?,?,now(),?,?);";
		Connection c = Banco.getConnection();
		PreparedStatement ps = Banco.getPreparedStmtPK(c, sql);
		try {
			ps.setString(1, p.getTitulo());
			ps.setString(2, p.getConteudo());
			ps.setInt(3, p.getUsuario().getId());
			ps.setInt(4, p.getCategoria().getId());
			
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
	
	public ArrayList<Pergunta> busca()
	{
		String sql = "select "
				+ "	p.* "
				+ "	,case when u.ativo = 0 then '[DELETADO]' else u.nome end as nome "
				+ " ,c.nome as categoria "
				+ "from pergunta p "
				+ "left join usuario u on u.id = p.id_usuario "
				+ "left join categoria c on c.id = p.id_categoria;";
		ArrayList<Pergunta> perguntas = new ArrayList<Pergunta>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		try 
		{
			resultado = stmt.executeQuery(sql);
			while(resultado.next())
			{
				Pergunta pergunta = montaPergunta(resultado);
				pergunta.getUsuario().setNome(resultado.getString("nome"));
				pergunta.getCategoria().setNome(resultado.getString("categoria"));
				perguntas.add(pergunta);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro no método busca da classe PerguntaDAO");
			System.out.println(e.getMessage());
		}
		finally 
		{
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return perguntas;
	}
	
	public ArrayList<Pergunta> busca(String texto, String categoria, boolean resolvido)
	{
		String sql ="select "
				+ "	p.* "
				+ ",case when u.ativo = 0 then '[DELETADO]' else u.nome end as nome "
				+ ",c.nome as categoria "
				+ "from pergunta p "
				+ "left join usuario u on u.id = p.id_usuario "
				+ "left join categoria c on c.id = p.id_categoria "
				+ "where (p.titulo like '%" + texto + "%' or p.conteudo like '%" + texto + "%') "
				+ "and c.nome = '" + categoria + "' ";
		if(resolvido)
		{
			sql += " and p.data_resolucao is not null";
		}
		else 
		{
			sql += " and p.data_resolucao is null";
		}
		ArrayList<Pergunta> perguntas = new ArrayList<Pergunta>();
		Connection conn = Banco.getConnection();
		Statement pstmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		try 
		{
			resultado = pstmt.executeQuery(sql);
			while(resultado.next())
			{
				Pergunta pergunta = montaPergunta(resultado);
				pergunta.getUsuario().setNome(resultado.getString("nome"));
				pergunta.getCategoria().setNome(resultado.getString("categoria"));
				perguntas.add(pergunta);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro no método busca da classe PerguntaDAO");
			System.out.println(e.getMessage());
		}
		finally 
		{
			Banco.closeResultSet(resultado);
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return perguntas;
	}
	
	public ArrayList<Pergunta> busca(int idUsuario)
	{
		String sql = "select "
				+ "	p.* "
				+ "	,case when u.ativo = 0 then '[DELETADO]' else u.nome end as nome "
				+ " ,c.nome as categoria "
				+ "from pergunta p "
				+ "left join usuario u on u.id = p.id_usuario "
				+ "left join categoria c on c.id = p.id_categoria"
				+ " where id_usuario = " + idUsuario + ";";
		ArrayList<Pergunta> perguntas = new ArrayList<Pergunta>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		try 
		{
			resultado = stmt.executeQuery(sql);
			while(resultado.next())
			{
				Pergunta pergunta = montaPergunta(resultado);
				pergunta.getUsuario().setNome(resultado.getString("nome"));
				pergunta.getCategoria().setNome(resultado.getString("categoria"));
				perguntas.add(pergunta);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro no método busca da classe PerguntaDAO");
			System.out.println(e.getMessage());
		}
		finally 
		{
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return perguntas;
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
	
	public Pergunta montaPergunta(ResultSet rs) throws SQLException
	{
		Pergunta pergunta = new Pergunta();
		Usuario usuario = new Usuario();
		Categoria categoria = new Categoria();
		
		pergunta.setId(rs.getInt("id"));
		pergunta.setTitulo(rs.getString("titulo"));
		pergunta.setConteudo(rs.getString("conteudo"));
		pergunta.setData(LocalDateTime.parse(rs.getString("data_pergunta"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		if(rs.getString("data_resolucao") != null)
			pergunta.setDataResolucao(LocalDateTime.parse(rs.getString("data_resolucao"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		usuario.setId(rs.getInt("id_usuario"));
		categoria.setId(rs.getInt("id_categoria"));
		pergunta.setCategoria(categoria);
		pergunta.setUsuario(usuario);
		
		return pergunta;
	}
}
