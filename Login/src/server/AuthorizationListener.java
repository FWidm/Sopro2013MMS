package server;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class AuthorizationListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId();
		System.out.println("currenPage: " + currentPage);

		boolean isLoginPage = (currentPage.lastIndexOf("/index.xhtml") > -1);
		System.out.println("isLoginPage: " + isLoginPage);
		
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

		if(session==null){
			NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
			nh.handleNavigation(facesContext, null, "index");
		}
		else{
			// get session attributes from current user
			String currentUser = (String) session.getAttribute("role");
			System.out.println("currenUser: " + currentUser);
			
			if (!isLoginPage) {
				NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
				if(currentUser == null || currentUser == ""){
					nh.handleNavigation(facesContext, null, "index");
				}else{
					if((currentUser.equals("Administrator")) && !(currentPage.lastIndexOf("admin-index.xhtml") > -1)){
						nh.handleNavigation(facesContext, null, "admin-index");
					}
					if((currentUser.equals("Redakteur")) && !(currentPage.lastIndexOf("redakteur-index.xhtml") > -1)){
						nh.handleNavigation(facesContext, null, "redakteur-index");
					}
					if((currentUser.equals("Dezernat")) && !(currentPage.lastIndexOf("dezernat-index.xhtml") > -1)){
						nh.handleNavigation(facesContext, null, "dezernat-index");
					}
					if((currentUser.equals("Dekan")) && !(currentPage.lastIndexOf("dekan-index.xhtml") > -1)){
						nh.handleNavigation(facesContext, null, "dekan-index");
					}
					if((currentUser.equals("Modulverantwortlicher")) && !(currentPage.lastIndexOf("modulverantwortlicher-index.xhtml") > -1)){
						nh.handleNavigation(facesContext, null, "modulverantwortlicher-index");
					}
				}				
			}
			
		}
	}

	public void beforePhase(PhaseEvent event) {

	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
}
