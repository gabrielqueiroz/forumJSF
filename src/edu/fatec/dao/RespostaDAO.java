package edu.fatec.dao;

import java.util.List;

import edu.fatec.model.Pergunta;
import edu.fatec.model.Resposta;
import edu.fatec.model.Usuario;

public interface RespostaDAO {
	public void inserirResposta(Resposta R, Pergunta P, Usuario U);
	public List<Resposta> listaRespostas(int id);
}
