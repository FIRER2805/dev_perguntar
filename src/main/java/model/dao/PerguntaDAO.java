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
	
	public int criar(Pergunta p)
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
}
