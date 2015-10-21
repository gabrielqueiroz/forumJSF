package edu.fatec.dao;

import java.sql.SQLException;

import edu.fatec.model.Usuario;

public interface UsuarioDAO {
	public void inserir(Usuario u) throws SQLException;
	public void remover(Usuario u) throws SQLException;
	public void atualizar(long id, Usuario u) throws SQLException;		
	public Usuario pesquisaUsuario(Usuario u) throws SQLException;
	public Usuario pesquisaUsuarioPergunta(int id) throws SQLException;
	public Usuario pesquisaUsuarioResposta(int id) throws SQLException;
	public boolean verificaCadastro(String usuario) throws SQLException;
	public boolean validaUsuario(String nome, String senha) throws SQLException;
}
