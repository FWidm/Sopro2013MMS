package server;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import ctrl.DBExRules;
import ctrl.DBModManual;
import ctrl.DBModule;

public class TemplateActionListenerBack implements ActionListener {
	
	public void refreshMenu(String itemValue) {
		
		TemplateActionListener listener = new TemplateActionListener();
		
		TemplateBean bean = findBean("TemplateBean");
		
		if(itemValue.equals(TemplateBean.PRUEFORDNUNG)) {
			
			bean.setExRules(null);
			bean.setModMan(null);
			bean.setModule(null);
			
			bean.setBackModel(new DefaultMenuModel());
			
			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(TemplateBean.PRUEFORDNUNG);
			model.addSubmenu(submenu);
			
			bean.setExRulesList(DBExRules.loadExRules());
			
			for(int i = 0; i < bean.getExRulesList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setValue(bean.getExRulesList().get(i).getExRulesTitle());
				m.setAjax(true);
				m.setUpdate("list-menu back-menu");
				m.addActionListener(listener);
				submenu.getChildren().add(m);
			}
			bean.setModel(model);
		}
		else if(itemValue.equals(TemplateBean.MODMANUAL)) {
			
			bean.setModMan(null);
			bean.setModule(null);
			
			bean.setModManList(DBModManual.loadModManuals(bean.getExRules()));
			
			MenuModel backModel = new DefaultMenuModel();
			Submenu backSubmenu = new Submenu();
			backSubmenu.setLabel("Zurück zu:");
			MenuItem exRules = new MenuItem();
			exRules.setAjax(true);
			exRules.setUpdate("list-menu back-menu");
			exRules.setValue(TemplateBean.PRUEFORDNUNG);
			exRules.addActionListener(this);
			backSubmenu.getChildren().add(exRules);
			backModel.addSubmenu(backSubmenu);
			bean.setBackModel(backModel);
			
			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(TemplateBean.MODMANUAL);
			model.addSubmenu(submenu);
			
			for(int i = 0; i < bean.getModManList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate("list-menu back-menu");
				m.setValue(bean.getModManList().get(i).getModManTitle());
				m.addActionListener(listener);
				submenu.getChildren().add(m);
			}
			
			bean.setModel(model);
		}
		else if(itemValue.equals(TemplateBean.MODULE)) {
			
			bean.setModule(null);
			
			bean.setModuleList((DBModule.loadModulesByManTitle((bean.getModMan()))));
			
			MenuModel backModel = new DefaultMenuModel();
			Submenu backSubmenu = new Submenu();
			backSubmenu.setLabel("Zurück zu:");
			MenuItem exRules = new MenuItem();
			exRules.setAjax(true);
			exRules.setUpdate("list-menu back-menu");
			exRules.setValue(TemplateBean.PRUEFORDNUNG);
			exRules.addActionListener(this);
			backSubmenu.getChildren().add(exRules);
			MenuItem modMan = new MenuItem();
			modMan.setAjax(true);
			modMan.setUpdate("list-menu back-menu");
			modMan.setValue(TemplateBean.MODMANUAL);
			modMan.addActionListener(this);
			backSubmenu.getChildren().add(modMan);
			backModel.addSubmenu(backSubmenu);
			bean.setBackModel(backModel);
			
			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(TemplateBean.MODULE);
			model.addSubmenu(submenu);
			
			for(int i = 0; i < bean.getModuleList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate("list-menu back-menu");
				m.setValue(bean.getModuleList().get(i).getModTitle());
				m.addActionListener(listener);
				submenu.getChildren().add(m);
			}
			
			bean.setModel(model);
		}
		else {
			System.out.println("Should never happen: Check menu!");
		}
	}
	
	@Override
	public void processAction(ActionEvent arg0) throws AbortProcessingException {
		// TODO Auto-generated method stub
		refreshMenu((String)((MenuItem)arg0.getSource()).getValue());
		System.out.println((String)((MenuItem)arg0.getSource()).getValue());
	}
	
    @SuppressWarnings("unchecked")
    public static <T> T findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }

}
