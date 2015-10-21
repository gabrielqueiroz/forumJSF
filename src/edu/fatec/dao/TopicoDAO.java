package edu.fatec.dao;

import java.sql.SQLException;

import edu.fatec.model.Topico;

public interface TopicoDAO {
	public Topico pesquisaTopicoPergunta(int id) throws SQLException;
}
