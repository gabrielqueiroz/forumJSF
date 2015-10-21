package edu.fatec.control;

import javax.faces.bean.ManagedBean;

import edu.fatec.model.Topico;

@ManagedBean
public class TopicoMB {
	private Topico topicoAtual;

	public TopicoMB() {
		setTopicoAtual(new Topico());
	}

	public Topico getTopicoAtual() {
		return topicoAtual;
	}

	public void setTopicoAtual(Topico topicoAtual) {
		this.topicoAtual = topicoAtual;
	}

}
