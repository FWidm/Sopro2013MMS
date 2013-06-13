package ctrl;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import db.DBExRules;
import db.DBModManual;
import db.DBModule;

import server.CreateSubRedBean;


public class CreateSubRedActionListenerBack implements ActionListener {
	
	public void refreshMenu(String itemValue) {
		
		CreateSubRedActionListener listener = new CreateSubRedActionListener();
		
		CreateSubRedBean bean = findBean("CreateSubRedBean");
		
		if(itemValue.equals(CreateSubRedBean.PRUEFORDNUNG)) {
			
			bean.setExRules(null);
			bean.setModMan(null);
			bean.setModule(null);
			
			bean.setBackModel(new DefaultMenuModel());
			
			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(CreateSubRedBean.PRUEFORDNUNG);
			model.addSubmenu(submenu);
			
			bean.setExRulesList(DBExRules.loadAllExRules());
			
			for(int i = 0; i < bean.getExRulesList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setValue(bean.getExRulesList().get(i).getExRulesTitle());
				m.setAjax(true);
				m.setUpdate(CreateSubRedBean.UPDATE_AJAX);
				m.addActionListener(listener);
				submenu.getChildren().add(m);
			}
			bean.setModel(model);
		}
		else if(itemValue.equals(CreateSubRedBean.MODMANUAL)) {
			
			bean.setModMan(null);
			bean.setModule(null);
			
			bean.setModManList(DBModManual.loadModManuals(bean.getExRules()));
			
			MenuModel backModel = new DefaultMenuModel();
			Submenu backSubmenu = new Submenu();
			backSubmenu.setLabel("Zur\u00FCck zu:");
			MenuItem exRules = new MenuItem();
			exRules.setAjax(true);
			exRules.setUpdate(CreateSubRedBean.UPDATE_AJAX);
			exRules.setValue(CreateSubRedBean.PRUEFORDNUNG);
			exRules.addActionListener(this);
			backSubmenu.getChildren().add(exRules);
			backModel.addSubmenu(backSubmenu);
			bean.setBackModel(backModel);
			
			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(CreateSubRedBean.MODMANUAL);
			model.addSubmenu(submenu);
			
			for(int i = 0; i < bean.getModManList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate(CreateSubRedBean.UPDATE_AJAX);
				m.setValue(bean.getModManList().get(i).getModManTitle());
				m.addActionListener(listener);
				submenu.getChildren().add(m);
			}
			
			bean.setModel(model);
		}
		else if(itemValue.equals(CreateSubRedBean.MODULE)) {
			
			bean.setModule(null);
			
			bean.setModuleList((DBModule.loadModulesByManTitle(bean.getExRules(), bean.getModMan())));
			
			MenuModel backModel = new DefaultMenuModel();
			Submenu backSubmenu = new Submenu();
			backSubmenu.setLabel("Zur\u00FCck zu:");
			MenuItem exRules = new MenuItem();
			exRules.setAjax(true);
			exRules.setUpdate(CreateSubRedBean.UPDATE_AJAX);
			exRules.setValue(CreateSubRedBean.PRUEFORDNUNG);
			exRules.addActionListener(this);
			backSubmenu.getChildren().add(exRules);
			MenuItem modMan = new MenuItem();
			modMan.setAjax(true);
			modMan.setUpdate(CreateSubRedBean.UPDATE_AJAX);
			modMan.setValue(CreateSubRedBean.MODMANUAL);
			modMan.addActionListener(this);
			backSubmenu.getChildren().add(modMan);
			backModel.addSubmenu(backSubmenu);
			bean.setBackModel(backModel);
			
			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(CreateSubRedBean.MODULE);
			model.addSubmenu(submenu);
			
			for(int i = 0; i < bean.getModuleList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate(CreateSubRedBean.UPDATE_AJAX);
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
