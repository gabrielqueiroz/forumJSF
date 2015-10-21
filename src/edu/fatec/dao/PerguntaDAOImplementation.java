package edu.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.fatec.model.Pergunta;
import edu.fatec.model.Topico;
import edu.fatec.model.Usuario;

public class PerguntaDAOImplementation implements PerguntaDAO {
	
	/**
	 * Metodo para retornar data e hora atual.
	 * @return
	 */
	public Date getToday() {
		return new Date();
	}

	@Override
	public void adicionarPergunta(Pergunta p, Usuario u, Topico t) {
		try {
		Connection con = JDBCUtil.getInstance().getConnection();		
		PreparedStatement pstmt = con
				.prepareStatement("INSERT INTO tb_pergunta (ID_USUARIO, ID_TOPICO, TITULO, DESCRICAO, DATAMENS, SITUACAO) "
						+ " VALUES (?,?,?,?,?,?);");		
		pstmt.setInt(1, u.getId_usuario());
		pstmt.setInt(2, t.getId_topico());
		pstmt.setString(3, p.getTitulo());		
		pstmt.setString(4, p.getDescricao());
		java.sql.Date dataBanco = new java.sql.Date(getToday().getTime());
		pstmt.setDate(5, dataBanco);
		pstmt.setString(6, "SEMRESP");						
		pstmt.executeUpdate();						
		} catch (SQLException e) {
			System.out.println("Erro ao inserir o usuário");	
			System.out.println(e);					
		}				
	}

	@Override
	public List<Pergunta> listaPergunta() {
		try {
		Connection con = JDBCUtil.getInstance().getConnection();		
		PreparedStatement pstmt = con.prepareStatement("SELECT * FROM TB_PERGUNTA");
		ResultSet rs = pstmt.executeQuery();
		List<Pergunta> perguntas =  new ArrayList<Pergunta>();
		while(rs.next()){
			Pergunta p = new Pergunta();		
			p.setId_pergunta(rs.getInt("id_pergunta"));			
			p.setTitulo(rs.getString("titulo"));			
			p.setDescricao(rs.getString("descricao"));
			p.setDatamens(rs.getDate("datamens"));
			p.setSituacao(rs.getString("situacao"));
			perguntas.add(p);
		}
			return perguntas;
		} catch (SQLException e) {
			System.out.println("Erro ao carregar lista de perguntas");
			System.out.println(e);			
			return null;
		}		
	}		
}