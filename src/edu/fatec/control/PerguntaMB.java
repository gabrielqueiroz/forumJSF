package edu.fatec.control;

import java.sql.SQLException;
import java.util.List;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.fatec.dao.PerguntaDAOImplementation;
import edu.fatec.dao.TopicoDAOImplementation;
import edu.fatec.dao.UsuarioDAOImplementation;
import edu.fatec.model.Pergunta;
import edu.fatec.model.Topico;
import edu.fatec.model.Usuario;

@SessionScoped
@ManagedBean
public class PerguntaMB {
	private Pergunta perguntaAtual;
	private List<Pergunta> perguntas;
	private Usuario usuarioAtual;
	private Topico topicoAtual;
	private PerguntaDAOImplementation DAO = new PerguntaDAOImplementation();

	public PerguntaMB() {
		setPerguntaAtual(new Pergunta());
		setPerguntas(DAO.listaPergunta());
	}

	public String adicionaPergunta() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ELContext elContext = facesContext.getELContext();
		UsuarioMB USMB = (UsuarioMB) facesContext.getApplication()
				.getELResolver().getValue(elContext, null, "usuarioMB");
		this.usuarioAtual = USMB.getUsuarioAtual();
		TopicoMB TPMB = (TopicoMB) facesContext.getApplication()
				.getELResolver().getValue(elContext, null, "topicoMB");
		this.topicoAtual = TPMB.getTopicoAtual();
		DAO.adicionarPergunta(getPerguntaAtual(), usuarioAtual, topicoAtual);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Pergunta adicionada com sucesso", null));
		return "./forum.xhtml";
	}

	public List<Pergunta> listaPerguntas() {
		setPerguntas(DAO.listaPergunta());
		return getPerguntas();
	}
	
	public String novaPergunta(){
		setPerguntaAtual(new Pergunta());
		return "./novaPergunta.xhtml";
	}

	public String visualizarPergunta(Pergunta p) {
		setPerguntaAtual(p);
		return "./perguntaResposta.xhtml";
	}

	public String ownerPergunta(Pergunta p) throws SQLException {
		UsuarioDAOImplementation DAOUser = new UsuarioDAOImplementation();
		if (DAOUser.pesquisaUsuarioPergunta(p.getId_pergunta()) != null) {
			return DAOUser.pesquisaUsuarioPergunta(p.getId_pergunta())
					.getUsuario();
		} else {
			return " ";
		}
	}

	public String ownerCategoria(Pergunta p) throws SQLException {
		TopicoDAOImplementation DAOTopico = new TopicoDAOImplementation();
		if (DAOTopico.pesquisaTopicoPergunta(p.getId_pergunta()) != null) {
			return DAOTopico.pesquisaTopicoPergunta(p.getId_pergunta())
					.getCategoria();
		} else {
			return " ";
		}

	}

	public Pergunta getPerguntaAtual() {
		return perguntaAtual;
	}

	public void setPerguntaAtual(Pergunta perguntaAtual) {
		this.perguntaAtual = perguntaAtual;
	}

	public Topico getTopicoAtual() {
		return topicoAtual;
	}

	public void setTopicoAtual(Topico topicoAtual) {
		this.topicoAtual = topicoAtual;
	}

	public Usuario getUsuarioAtual() {
		return usuarioAtual;
	}

	public void setUsuarioAtual(Usuario usuarioAtual) {
		this.usuarioAtual = usuarioAtual;
	}

	public List<Pergunta> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}
}
