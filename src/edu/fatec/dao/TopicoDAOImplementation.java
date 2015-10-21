package edu.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import edu.fatec.model.Topico;


public class TopicoDAOImplementation implements TopicoDAO{

	@Override
	public Topico pesquisaTopicoPergunta(int id) throws SQLException {
		try {		
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = 
					con.prepareStatement("SELECT DISTINCT ID_TOPICO, CATEGORIA FROM tb_topico" +
					" NATURAL JOIN tb_pergunta WHERE tb_pergunta.id_pergunta=?");
			pstmt.setInt(1, id);		
			ResultSet rs = pstmt.executeQuery();
			Topico t = null;
			if(rs.next()){
				t = new Topico();
				t.setId_topico(rs.getInt("id_topico"));
				t.setCategoria(rs.getString("categoria"));				
			}
			return t;
			} catch (SQLException e) {
				System.out.println("Erro ao pesquisar usuario");	
				System.out.println(e);					
				return null;
			}		
	}

}
