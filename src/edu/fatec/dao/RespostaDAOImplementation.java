package edu.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.fatec.model.Pergunta;
import edu.fatec.model.Resposta;
import edu.fatec.model.Usuario;

public class RespostaDAOImplementation implements RespostaDAO {

	/**
	 * Metodo para retornar data e hora atual.
	 * @return
	 */
	public Date getToday() {
		return new Date();
	}
	
	@Override
	public void inserirResposta(Resposta r, Pergunta p, Usuario u) {
		try {
			System.out.println("ID PERGUNTA "+p.getId_pergunta());
			Connection con = JDBCUtil.getInstance().getConnection();		
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO tb_resposta (ID_PERGUNTA, ID_USUARIO, RESPOSTA, DATAMENS, AVALIACAO) "
							+ " VALUES (?,?,?,?,?);");					
			pstmt.setInt(1, p.getId_pergunta());
			pstmt.setInt(2, u.getId_usuario());		
			pstmt.setString(3, r.getResposta());
			java.sql.Date dataBanco = new java.sql.Date(getToday().getTime());
			pstmt.setDate(4, dataBanco);
			pstmt.setString(5, "SEMAVAL");						
			pstmt.executeUpdate(); 
			
			PreparedStatement pstmt2 = con.prepareStatement("UPDATE tb_pergunta SET situacao='RESP' WHERE id_pergunta=?");
			pstmt2.setInt(1, p.getId_pergunta());			
			pstmt2.executeUpdate();
			
			} catch (SQLException e) {
				System.out.println("Erro ao inserir o Resposta");	
				System.out.println(e);					
			}							
	}

	@Override
	public List<Resposta> listaRespostas(int id) {
		try {
		Connection con = JDBCUtil.getInstance().getConnection();		
		PreparedStatement pstmt = con.prepareStatement("SELECT * FROM TB_RESPOSTA WHERE ID_PERGUNTA=?");
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		List<Resposta> respostas =  new ArrayList<Resposta>();
		while(rs.next()){
			Resposta r = new Resposta();		
			r.setId_resposta(rs.getInt("id_resposta"));
			r.setResposta(rs.getString("resposta"));
			r.setAvaliacao(rs.getString("avaliacao"));
			r.setDatamens(rs.getDate("datamens"));
			respostas.add(r);
		}
			return respostas;
		} catch (SQLException e) {
			System.out.println("Erro ao carregar lista de respostas");
			System.out.println(e);			
			return null;
		}		
	}
}
