package edu.fatec.control;

import java.sql.SQLException;
import java.util.List;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import edu.fatec.dao.RespostaDAOImplementation;
import edu.fatec.dao.UsuarioDAOImplementation;
import edu.fatec.model.Pergunta;
import edu.fatec.model.Resposta;
import edu.fatec.model.Usuario;

@ViewScoped
@ManagedBean
public class RespostaMB {	
	private Resposta respostaAtual;
	private List<Resposta> respostas;
	private Usuario usuario;
	private RespostaDAOImplementation DAO = new RespostaDAOImplementation();

	public RespostaMB() {
		setRespostaAtual(new Resposta());
	}

	public String adicionaResposta() {
		FacesContext facesContext = FacesContext.getCurrentInstance();	
		ELContext elContext = facesContext.getELContext();
		UsuarioMB USMB = (UsuarioMB) facesContext.getApplication()
				.getELResolver().getValue(elContext, null, "usuarioMB");
		Usuario usuarioAtual = USMB.getUsuarioAtual();
		PerguntaMB PMB = (PerguntaMB) facesContext.getApplication()
				.getELResolver().getValue(elContext, null, "perguntaMB");
		Pergunta perguntaAtual = PMB.getPerguntaAtual();
		System.out.println(perguntaAtual.getId_pergunta());
		System.out.println(perguntaAtual.getTitulo());
		System.out.println(perguntaAtual.getDescricao());
		DAO.inserirResposta(getRespostaAtual(), perguntaAtual, usuarioAtual);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Resposta adicionada com sucesso.",null));
		return "./perguntaResposta.xhtml";
	}

	public List<Resposta> respostaPergunta() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ELContext elContext = facesContext.getELContext();
		PerguntaMB PMB = (PerguntaMB) facesContext.getApplication()
				.getELResolver().getValue(elContext, null, "perguntaMB");
		Pergunta perguntaAtual = PMB.getPerguntaAtual();
		setRespostas(DAO.listaRespostas(perguntaAtual.getId_pergunta()));
		return getRespostas();
	}

	public String ownerResposta(Resposta r) throws SQLException {
		UsuarioDAOImplementation DAOUser = new UsuarioDAOImplementation();		
		if (DAOUser.pesquisaUsuarioResposta(r.getId_resposta()) != null){
			return DAOUser.pesquisaUsuarioResposta(r.getId_resposta()).getNome();				
		}else {
			return " ";
		}		
	}

	public Resposta getRespostaAtual() {
		return respostaAtual;
	}

	public void setRespostaAtual(Resposta respostaAtual) {
		this.respostaAtual = respostaAtual;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
