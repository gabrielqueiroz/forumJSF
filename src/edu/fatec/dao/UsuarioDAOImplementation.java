package edu.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.fatec.model.Usuario;

public class UsuarioDAOImplementation implements UsuarioDAO {

	@Override
	public void inserir(Usuario u) throws SQLException {
		Connection con = JDBCUtil.getInstance().getConnection();
		PreparedStatement pstmt = con
				.prepareStatement("INSERT INTO tb_usuario (NOME, PAIS, ESTADO, USUARIO, SENHA, EMAIL, DATANASC) "
						+ " VALUES (?,?,?,?,?,?,?);");		
		pstmt.setString(1, u.getNome());
		pstmt.setString(2, u.getPais());
		pstmt.setString(3, u.getEstado());		
		pstmt.setString(4, u.getUsuario());
		pstmt.setString(5, u.getSenha());
		pstmt.setString(6, u.getEmail());
		java.sql.Date dataBanco = new java.sql.Date(u.getDatanasc().getTime());
		pstmt.setDate(7, dataBanco);		
		pstmt.executeUpdate();		
	}

	@Override
	public void remover(Usuario u) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void atualizar(long id, Usuario u) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Usuario pesquisaUsuario(Usuario user) throws SQLException {
		Connection con = JDBCUtil.getInstance().getConnection();
		PreparedStatement pstmt = con.prepareStatement("SELECT ID_USUARIO, NOME, PAIS, ESTADO, USUARIO, SENHA, EMAIL, DATANASC FROM tb_usuario " +
				"WHERE USUARIO LIKE ? AND SENHA LIKE ? ");
		pstmt.setString(1, user.getUsuario());
		pstmt.setString(2, user.getSenha());
		ResultSet rs = pstmt.executeQuery();
		Usuario u = null;
		if(rs.next()){
			u = new Usuario();
			u.setId_usuario(rs.getInt("ID_USUARIO"));
			u.setNome(rs.getString("NOME"));
			u.setPais(rs.getString("PAIS"));
			u.setEstado(rs.getString("ESTADO"));
			u.setUsuario(rs.getString("USUARIO"));
			u.setSenha(rs.getString("SENHA"));
			u.setEmail(rs.getString("ESTADO"));
			u.setDatanasc(rs.getDate("DATANASC"));
		}
		return u;
	}

	@Override
	public boolean validaUsuario(String nome, String senha) throws SQLException {
		Connection con = JDBCUtil.getInstance().getConnection();
		PreparedStatement pstmt = con.prepareStatement("SELECT ID_USUARIO, NOME, PAIS, ESTADO, USUARIO, SENHA, EMAIL, DATANASC FROM tb_usuario " +
				"WHERE USUARIO LIKE ? AND SENHA LIKE ? ");
		pstmt.setString(1, nome);
		pstmt.setString(2, senha);
		ResultSet rs = pstmt.executeQuery();		
		if(rs.next()){
			return true;
		} else {
		return false;
		}
	}

	@Override
	public boolean verificaCadastro(String usuario) throws SQLException {
		Connection con = JDBCUtil.getInstance().getConnection();
		PreparedStatement pstmt = con.prepareStatement("SELECT * FROM tb_usuario WHERE USUARIO LIKE ?");
		pstmt.setString(1, usuario);		
		ResultSet rs = pstmt.executeQuery();		
		if(rs.next()){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Usuario pesquisaUsuarioPergunta(int id){
		try {		
		Connection con = JDBCUtil.getInstance().getConnection();
		PreparedStatement pstmt = 
				con.prepareStatement("SELECT DISTINCT ID_USUARIO, NOME, PAIS, ESTADO, USUARIO, SENHA, EMAIL, DATANASC FROM tb_usuario" +
				" NATURAL JOIN tb_pergunta WHERE tb_pergunta.id_pergunta=?");
		pstmt.setInt(1, id);		
		ResultSet rs = pstmt.executeQuery();
		Usuario u = null;
		if(rs.next()){
			u = new Usuario();
			u.setId_usuario(rs.getInt("ID_USUARIO"));
			u.setNome(rs.getString("NOME"));
			u.setPais(rs.getString("PAIS"));
			u.setEstado(rs.getString("ESTADO"));
			u.setUsuario(rs.getString("USUARIO"));
			u.setSenha(rs.getString("SENHA"));
			u.setEmail(rs.getString("ESTADO"));
			u.setDatanasc(rs.getDate("DATANASC"));			
		}
		return u;
		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar usuario");	
			System.out.println(e);					
			return null;
		}
	}

	@Override
	public Usuario pesquisaUsuarioResposta(int id) throws SQLException {
		try {		
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = 
					con.prepareStatement("SELECT DISTINCT ID_USUARIO, NOME, PAIS, ESTADO, USUARIO, SENHA, EMAIL, DATANASC FROM tb_usuario" +
					" NATURAL JOIN tb_resposta WHERE tb_resposta.id_resposta=?");
			pstmt.setInt(1, id);		
			ResultSet rs = pstmt.executeQuery();
			Usuario u = null;
			if(rs.next()){
				u = new Usuario();
				u.setId_usuario(rs.getInt("ID_USUARIO"));
				u.setNome(rs.getString("NOME"));
				u.setPais(rs.getString("PAIS"));
				u.setEstado(rs.getString("ESTADO"));
				u.setUsuario(rs.getString("USUARIO"));
				u.setSenha(rs.getString("SENHA"));
				u.setEmail(rs.getString("ESTADO"));
				u.setDatanasc(rs.getDate("DATANASC"));			
			}
			return u;
			} catch (SQLException e) {
				System.out.println("Erro ao pesquisar usuario");	
				System.out.println(e);					
				return null;
			}
	}

}
