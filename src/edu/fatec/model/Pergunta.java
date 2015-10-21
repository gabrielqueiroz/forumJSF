package edu.fatec.model;

import java.util.Date;

public class Pergunta {
	private int id_pergunta;
	private String titulo;
	private String descricao;	
	private String situacao;	
	private Date datamens;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDatamens() {
		return datamens;
	}
	public void setDatamens(Date datamens) {
		this.datamens = datamens;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public int getId_pergunta() {
		return id_pergunta;
	}
	public void setId_pergunta(int id_pergunta) {
		this.id_pergunta = id_pergunta;
	}
}
