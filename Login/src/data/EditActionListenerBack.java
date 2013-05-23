package data;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import server.EditBean;

import ctrl.DBExRules;
import ctrl.DBModManual;
import ctrl.DBModule;

public class EditActionListenerBack implements ActionListener {
	
	public void refreshMenu(String itemValue) {
		
		EditActionListener listener = new EditActionListener();
		
		EditBean bean = findBean("EditBean");
		
		if(itemValue.equals(EditBean.PRUEFORDNUNG)) {
			
			bean.setExRules(null);
			bean.setModMan(null);
			bean.setModule(null);
			
			bean.setBackModel(new DefaultMenuModel());
			
			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(EditBean.PRUEFORDNUNG);
			model.addSubmenu(submenu);
			
			bean.setExRulesList(DBExRules.loadAllExRules());
			
			for(int i = 0; i < bean.getExRulesList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setValue(bean.getExRulesList().get(i).getExRulesTitle());
				m.setAjax(true);
				m.setUpdate(EditBean.UPDATE_AJAX);
				m.addActionListener(listener);
				submenu.getChildren().add(m);
			}
			bean.setModel(model);
		}
		else if(itemValue.equals(EditBean.MODMANUAL)) {
			
			bean.setModMan(null);
			bean.setModule(null);
			
			bean.setModManList(DBModManual.loadModManuals(bean.getExRules()));
			
			MenuModel backModel = new DefaultMenuModel();
			Submenu backSubmenu = new Submenu();
			backSubmenu.setLabel("Zur\u00FCck zu:");
			MenuItem exRules = new MenuItem();
			exRules.setAjax(true);
			exRules.setUpdate(EditBean.UPDATE_AJAX);
			exRules.setValue(EditBean.PRUEFORDNUNG);
			exRules.addActionListener(this);
			backSubmenu.getChildren().add(exRules);
			backModel.addSubmenu(backSubmenu);
			bean.setBackModel(backModel);
			
			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(EditBean.MODMANUAL);
			model.addSubmenu(submenu);
			
			for(int i = 0; i < bean.getModManList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate(EditBean.UPDATE_AJAX);
				m.setValue(bean.getModManList().get(i).getModManTitle());
				m.addActionListener(listener);
				submenu.getChildren().add(m);
			}
			
			bean.setModel(model);
		}
		else if(itemValue.equals(EditBean.MODULE)) {
			
			bean.setModule(null);
			
			bean.setModuleList((DBModule.loadModulesByManTitle((bean.getModMan()))));
			
			MenuModel backModel = new DefaultMenuModel();
			Submenu backSubmenu = new Submenu();
			backSubmenu.setLabel("Zur\u00FCck zu:");
			MenuItem exRules = new MenuItem();
			exRules.setAjax(true);
			exRules.setUpdate(EditBean.UPDATE_AJAX);
			exRules.setValue(EditBean.PRUEFORDNUNG);
			exRules.addActionListener(this);
			backSubmenu.getChildren().add(exRules);
			MenuItem modMan = new MenuItem();
			modMan.setAjax(true);
			modMan.setUpdate(EditBean.UPDATE_AJAX);
			modMan.setValue(EditBean.MODMANUAL);
			modMan.addActionListener(this);
			backSubmenu.getChildren().add(modMan);
			backModel.addSubmenu(backSubmenu);
			bean.setBackModel(backModel);
			
			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(EditBean.MODULE);
			model.addSubmenu(submenu);
			
			for(int i = 0; i < bean.getModuleList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate(EditBean.UPDATE_AJAX);
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
	}
	
    @SuppressWarnings("unchecked")
    public static <T> T findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }

}
