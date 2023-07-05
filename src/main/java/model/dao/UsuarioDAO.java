package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Banco;
import model.vo.PesquisaUsuario;
import model.vo.Usuario;

public class UsuarioDAO {

	public int inserir(Usuario u) {
		String sql = "insert into usuario(nome, e_mail, senha) values(?, ?, ?)";
		int chaveRetornada = -1;
		Connection con = Banco.getConnection();
		PreparedStatement pStmt = Banco.getPreparedStmtPK(con, sql);
		ResultSet rs = null;
		try {
			pStmt.setString(1, u.getNome());
			pStmt.setString(2, u.geteMail());
			pStmt.setString(3, u.getSenha());

			pStmt.executeUpdate();

			rs = pStmt.getGeneratedKeys();

			if (rs.next()) {
				chaveRetornada = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Erro no método inserir da classe UsuarioDAO");
			System.out.println(e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closeStatement(pStmt);
			Banco.closeConnection(con);
		}
		return chaveRetornada;
	}
	
	public Usuario buscarPorId(int id)
	{
		String sql =  "select * from usuario where id = ?";
		Usuario usuario = null;
		Connection conn = Banco.getConnection();
		PreparedStatement pStmt = Banco.getPreparedStmt(conn, sql);
		ResultSet rs = null;
		try {
			pStmt.setInt(1, id);
			rs = pStmt.executeQuery();
			if(rs.next())
			{
				usuario = new Usuario();
				usuario.setId(rs.getInt(1));
				usuario.setNome(rs.getString(2));
				usuario.seteMail(rs.getString(3));
				usuario.setSenha(rs.getString(4));
			}
		}
		catch(SQLException e)
		{
			usuario = null;
			System.out.println("Erro no método buscarPorId da classe UsuarioDAO");
			System.out.println(e.getMessage());
		}
		finally 
		{
			Banco.closeResultSet(rs);
			Banco.closeStatement(pStmt);
			Banco.closeConnection(conn);
		}
		return usuario;
	}
	
	public int atualizar(Usuario u)
	{
		String sql =  "update usuario set nome = ?, e_mail = ?, senha = ? where id = ?";
		int registrosAfetados = 0;
		Connection conn = Banco.getConnection();
		PreparedStatement pStmt = Banco.getPreparedStmt(conn, sql);
		try {
			pStmt.setString(1, u.getNome());
			pStmt.setString(2, u.geteMail());
			pStmt.setString(3, u.getSenha());
			pStmt.setInt(4, u.getId());
			registrosAfetados = pStmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("Erro no método atualizar da classe UsuarioDAO");
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
		String sql =  "update usuario set ativo = 0 where id = ?";
		int registrosAfetados = 0;
		Connection conn = Banco.getConnection();
		PreparedStatement pStmt = Banco.getPreparedStmt(conn, sql);
		try {
			pStmt.setInt(1, id);
			registrosAfetados = pStmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("Erro no método excluir da classe UsuarioDAO");
			System.out.println(e.getMessage());
		}
		finally 
		{
			Banco.closeStatement(pStmt);
			Banco.closeConnection(conn);
		}
		return registrosAfetados;
	}
	
	public boolean eMailDisponivel(String eMail)
	{
		boolean retorno = true;
		String sql =  "select e_mail from usuario where e_mail = ?";
		Connection conn = Banco.getConnection();
		PreparedStatement pStmt = Banco.getPreparedStmt(conn, sql);
		ResultSet rs = null;
		try {
			pStmt.setString(1, eMail);
			rs = pStmt.executeQuery();
			if(rs.next())
			{
				retorno = false;
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro no método emailDisponivel da classe UsuarioDAO");
			System.out.println(e.getMessage());
		}
		finally 
		{
			Banco.closeStatement(pStmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}
	
	public Usuario login(Usuario u)
	{
		Usuario retorno = null;
		String sql =  "select * from usuario where e_mail = ? and senha = ?";
		Connection conn = Banco.getConnection();
		PreparedStatement pStmt = Banco.getPreparedStmt(conn, sql);
		ResultSet rs = null;
		try {
			pStmt.setString(1, u.geteMail());
			pStmt.setString(2, u.getSenha());
			rs = pStmt.executeQuery();
			if(rs.next())
			{
				u.setId(rs.getInt(1));
				u.setNome(rs.getString(2));
				u.seteMail(rs.getString(3));
				u.setSenha(rs.getString(4));
				retorno = u;
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro no método emailDisponivel da classe UsuarioDAO");
			System.out.println(e.getMessage());
		}
		finally 
		{
			Banco.closeStatement(pStmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}
	
	public boolean mudouEmail(Usuario u)
	{
		// TODO verificar se o email novo é igual ao que esta no banco ou não
		// para a atualização de usuario
		return false;
	}

	public ArrayList<Usuario> pesquisarUsuario(PesquisaUsuario pesquisaUsuario) {
		
		String sql = "select "
				+ "u.nome, "
				+ "count(p.id) as qtdpergunta, "
				+ "count(r.id) as qtdresposta, "
				+ "(select count(r2.id) from resposta r2 where r2.id_usuario = u.id and solucao = 1) as qtdsolucao "
				+ "from usuario u "
				+ "left join pergunta p on p.id_usuario = u.id "
				+ "left join resposta r on r.id_usuario = u.id "
				+ "group by u.nome, u.id ";
		
		String filtro = "";
		if(pesquisaUsuario.isTemPergunta()) {
			filtro += "qtdpergunta > 1 ";
		}
		if(!filtro.isEmpty()) {
			filtro += "and ";
		}
		if(pesquisaUsuario.isTemresposta()) {
			filtro += "qtdresposta > 1 ";
		}
		if(!filtro.isEmpty()) {
			filtro += "and ";
		}
		if(pesquisaUsuario.isTemSolucao()) {
			filtro += "qtdsolucao > 1 ";
		}
		if(!filtro.isEmpty()) {
			filtro = "having" + filtro;
		}
		filtro = filtro +"order by "+ pesquisaUsuario.getOrdemPesquisa();
		sql = sql+filtro;
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Connection conn = Banco.getConnection();
		Statement pstmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		try {
			resultado = pstmt.executeQuery(sql);
			while(resultado.next()){
				
				Usuario u = new Usuario();
				u.setNome(resultado.getString(1));
				u.setNumPergunta(resultado.getInt(2));
				u.setNumResposta(resultado.getInt(3));
				u.setNumsolucao(resultado.getInt(4));
				
				usuarios.add(u);
			}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		finally{
			Banco.closeResultSet(resultado);
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		
		
		return usuarios;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}