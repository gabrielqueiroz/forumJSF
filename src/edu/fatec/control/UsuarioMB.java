package edu.fatec.control;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.fatec.dao.UsuarioDAOImplementation;
import edu.fatec.model.Usuario;

@SessionScoped
@ManagedBean
public class UsuarioMB {
	private Usuario usuarioAtual;
	private UsuarioDAOImplementation DAO = new UsuarioDAOImplementation();
	private boolean logado;
	private String mensagem;

	public UsuarioMB() {
		setUsuarioAtual(new Usuario());
		setLogado(false);
	}

	public String adicionar() {
		try {
			if (DAO.verificaCadastro(getUsuarioAtual().getUsuario())) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario "
								+ usuarioAtual.getNome() + " já cadastrado.",
								"Insira um usuário diferente."));
				return "./novoUsuario.xhtml";
			} else {
				DAO.inserir(getUsuarioAtual());
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario "
								+ usuarioAtual.getNome()
								+ " cadastrado com sucesso.", null));
				setUsuarioAtual(new Usuario());
				return "./novoUsuario.xhtml";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "./erro.xhtml";
	}

	public String validaLogin() throws SQLException {
		if (DAO.validaUsuario(getUsuarioAtual().getUsuario(), getUsuarioAtual()
				.getSenha())) {
			setUsuarioAtual(DAO.pesquisaUsuario(getUsuarioAtual()));
			setLogado(true);
			return "./forum.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Usuario ou senha inválidos.", null));
			return "./index.xhtml";
		}
	}

	public String sair() {
		setUsuarioAtual(new Usuario());
		setLogado(false);
		return "./index.xhtml";
	}
	
	public void mensagemLogin() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Faça o Login para acessar o sistema.", null));
	}

	public Usuario getUsuarioAtual() {
		return usuarioAtual;
	}

	public void setUsuarioAtual(Usuario usuarioAtual) {
		this.usuarioAtual = usuarioAtual;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
