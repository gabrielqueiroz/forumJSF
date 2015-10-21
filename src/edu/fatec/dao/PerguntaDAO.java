package edu.fatec.dao;

import java.util.List;
import edu.fatec.model.Pergunta;
import edu.fatec.model.Topico;
import edu.fatec.model.Usuario;

interface PerguntaDAO {
	public void adicionarPergunta(Pergunta p, Usuario u, Topico t);	
	public List<Pergunta> listaPergunta();
}
