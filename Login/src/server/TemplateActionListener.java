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
import ctrl.DBModule;
import ctrl.DBSubject;

public class TemplateActionListener implements ActionListener {

	public void refreshMenu(String itemValue) {
		
		TemplateBean bean = findBean("TemplateBean");
		
		if(bean.getExRules() == null) {
			bean.setExRules(itemValue);
			
			bean.setModManList(DBModManual.loadModManuals(itemValue));
			
			Submenu exRules = new Submenu();
			exRules.setLabel(itemValue);
			
			for(int i = 0; i < bean.getModManList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate("list-menu");
				m.setValue(bean.getModManList().get(i).getModManTitle());
				m.addActionListener(this);
				exRules.getChildren().add(m);
			}
			
			MenuModel model = new DefaultMenuModel();
			model.addSubmenu(exRules);
			
			bean.setModel(model);
		}
		else if(bean.getModMan() == null) {
			
			bean.setModMan(itemValue);
			bean.setModuleList((DBModule.loadModulesByManTitle((itemValue))));
			
			Submenu exRules = new Submenu();
			exRules.setLabel(bean.getExRules());
			
			Submenu modMan = new Submenu();
			modMan.setLabel(itemValue);
			
			for(int i = 0; i < bean.getModuleList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate("list-menu");
				m.setValue(bean.getModuleList().get(i).getModTitle());
				m.addActionListener(this);
				modMan.getChildren().add(m);
			}
			
			MenuModel model = new DefaultMenuModel();
			model.addSubmenu(exRules);
			model.addSubmenu(modMan);
			
			bean.setModel(model);
		}
		else if(bean.getModule() == null) {
			
			bean.setModule(itemValue);
			bean.setSubjectList(((DBSubject.loadSubject(0, itemValue))));
			
			Submenu exRules = new Submenu();
			exRules.setLabel(bean.getExRules());
			
			Submenu modMan = new Submenu();
			modMan.setLabel(bean.getModMan());
			
			Submenu module = new Submenu();
			module.setLabel(itemValue);
			
			for(int i = 0; i < bean.getSubjectList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate("list-menu");
				m.setValue(bean.getSubjectList().get(i).getSubTitle());
				m.addActionListener(this);
				module.getChildren().add(m);
			}
			
			MenuModel model = new DefaultMenuModel();
			model.addSubmenu(exRules);
			model.addSubmenu(modMan);
			model.addSubmenu(module);
			
			bean.setModel(model);
		}
	}
	
	@Override
	public void processAction(ActionEvent arg0) throws AbortProcessingException {
		// TODO Auto-generated method stub
		refreshMenu((String)((MenuItem)arg0.getSource()).getValue());
	}
	
    @SuppressWarnings("unchecked")
    public static <T> T findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }

}
