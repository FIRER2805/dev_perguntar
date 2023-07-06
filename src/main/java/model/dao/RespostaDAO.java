package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import model.Banco;
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
	
	public int inserirNaResposta(Resposta r, int idRespostaPai)
	{
		int chave = 0;
		ResultSet chaves = null;
		String sql = "insert into resposta(conteudo, id_pergunta, solucao, id_resposta) values(?, ?, ?, ?);";
		Connection c = Banco.getConnection();
		PreparedStatement ps = Banco.getPreparedStmtPK(c, sql);
		try {
			ps.setString(1, r.getConteudo());
			ps.setInt(2, r.getIdPergunta());
			ps.setBoolean(3, r.isSolucao());
			ps.setInt(4,idRespostaPai);
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
	
	// cria as root e colocam elas numa lista
	public List<DefaultMutableTreeNode> montaArvoresResposta(int idPergunta)
	{
		ArrayList<DefaultMutableTreeNode> retorno = new ArrayList<DefaultMutableTreeNode>();
		String sql = "select * from resposta where id_resposta is null and id_pergunta = ?";
		Connection con = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStmt(con, sql);
		ResultSet rs = null;
		try {
			pstmt.setInt(1, idPergunta);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Resposta resposta = new Resposta();
				resposta.setId(rs.getInt("id"));
				resposta.setConteudo(rs.getString("conteudo"));
				resposta.setSolucao(rs.getBoolean("solucao"));
				DefaultMutableTreeNode root = new DefaultMutableTreeNode(resposta);
				montaArvoresRespostaHelper(rs.getInt("id"), root);
				retorno.add(root);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro no método MontaArvoresResposta na classe RespostaDAO");
			System.out.println(e.getMessage());
			retorno = null;
		}
		finally 
		{
			Banco.closeResultSet(rs);
			Banco.closeStatement(pstmt);
			Banco.closeConnection(con);
		}
		return retorno;
	}
	
	// conecta as roots com seus filhos
	private void montaArvoresRespostaHelper(int idResposta, DefaultMutableTreeNode node)
	{
		String sql = "select * from resposta where id_resposta = ?";
		Connection con = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStmt(con, sql);
		ResultSet rs = null;
		try {
			pstmt.setInt(1, idResposta);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Resposta resposta = new Resposta();
				resposta.setId(rs.getInt("id"));
				resposta.setConteudo(rs.getString("conteudo"));
				resposta.setSolucao(rs.getBoolean("solucao"));
				resposta.setIdPergunta(rs.getInt("id_resposta"));
				node.add(new DefaultMutableTreeNode(resposta));
				DefaultMutableTreeNode novoFilho = (DefaultMutableTreeNode) node.getLastChild();
				montaArvoresRespostaHelper(rs.getInt("id"), novoFilho);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro no método MontaArvoresResposta na classe RespostaDAO");
			System.out.println(e.getMessage());
		}
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
