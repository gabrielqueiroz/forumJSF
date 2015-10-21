package edu.fatec.model;

import java.util.Date;

public class Resposta {
	private int id_resposta;
	private String resposta;
	private Date datamens;
	private String avaliacao;
	
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	public Date getDatamens() {
		return datamens;
	}
	public void setDatamens(Date datamens) {
		this.datamens = datamens;
	}
	public String getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}
	public int getId_resposta() {
		return id_resposta;
	}
	public void setId_resposta(int id_resposta) {
		this.id_resposta = id_resposta;
	}
	
	
}
