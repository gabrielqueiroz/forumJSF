package edu.fatec.control;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class interceptador implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent e) {
		FacesContext ctx = e.getFacesContext();
		String pagina = ctx.getViewRoot().getViewId();		
		if (!pagina.equals("/index.xhtml")
				&& !pagina.equals("/novoUsuario.xhtml")) {
			System.out.println("Usuario esta acessando : " + pagina);
			UsuarioMB user = ctx.getApplication().evaluateExpressionGet(ctx,
					"${usuarioMB}", UsuarioMB.class);
			if (user.isLogado()) {
				System.out.println("Usuário logado: " + user.isLogado());
				user.setMensagem("");
			} else {
				System.out.println("Usuário logado: " + user.isLogado());
				user.setMensagem("Faça o login para acessar o sistema, ou clique em 'Clique aqui para se cadastrar'");
				NavigationHandler nav = ctx.getApplication()
						.getNavigationHandler();
				nav.handleNavigation(ctx, null,
						"./index.xhtml?faces-redirect=true");
				ctx.renderResponse();
			}
		}		
	}

	@Override
	public void beforePhase(PhaseEvent e) {
		if (e.getPhaseId() != PhaseId.RESTORE_VIEW) {
			FacesContext ctx = e.getFacesContext();
			String view = ctx.getViewRoot().getViewId();
			System.out.println(" Before : " + e.getPhaseId() + " (" + view
					+ ")");
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
