package server;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import ctrl.DBModManual;

public class TemplateActionListener implements ActionListener {

	public void loadModMan(String exRulesTitle) {
		
		TemplateBean bean = findBean("TemplateBean");
		
		bean.setExRules(exRulesTitle);
		
		bean.setModManList(DBModManual.loadAllModManuals(exRulesTitle));
		
		Submenu submenu = new Submenu();
		submenu.setLabel(exRulesTitle);
		
		for(int i = 0; i < bean.getModManList().size(); i++) {
			MenuItem m = new MenuItem();
			m.setAjax(true);
			m.setUpdate("list-menu");
			m.setValue(bean.getModManList().get(i).getModManTitle());
			m.addActionListener(this);
			submenu.getChildren().add(m);
		}
		
		MenuModel model = new DefaultMenuModel();
		model.addSubmenu(submenu);
		
		bean.setModel(model);
	}
	
	@Override
	public void processAction(ActionEvent arg0) throws AbortProcessingException {
		// TODO Auto-generated method stub
		loadModMan((String)((MenuItem)arg0.getSource()).getValue());
	}
	
    @SuppressWarnings("unchecked")
    public static <T> T findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }

}
