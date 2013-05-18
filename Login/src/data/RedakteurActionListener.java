package data;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import server.RedakteurBean;

import ctrl.DBExRules;
import ctrl.DBModManual;
import ctrl.DBModule;
import ctrl.DBSubject;

public class RedakteurActionListener implements ActionListener {
	
	public void refreshMenu(String itemValue) {
		
		RedakteurActionListenerBack back = new RedakteurActionListenerBack();
		
		RedakteurBean bean = findBean("RedakteurBean");
		
		if(bean.getExRules() == null) {
			bean.setExRules(itemValue);
			bean.setModManList(DBModManual.loadModManuals(itemValue));
			
			MenuModel backModel = new DefaultMenuModel();
			Submenu backSubmenu = new Submenu();
			backSubmenu.setLabel("Zur\u00FCck zu:");
			MenuItem exRules = new MenuItem();
			exRules.setAjax(true);
			exRules.setUpdate(RedakteurBean.UPDATE_AJAX);
			exRules.setValue(RedakteurBean.PRUEFORDNUNG);
			exRules.addActionListener(back);
			backSubmenu.getChildren().add(exRules);
			backModel.addSubmenu(backSubmenu);
			bean.setBackModel(backModel);
			
			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(RedakteurBean.MODMANUAL);
			model.addSubmenu(submenu);
			
			for(int i = 0; i < bean.getModManList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate(RedakteurBean.UPDATE_AJAX);
				m.setValue(bean.getModManList().get(i).getModManTitle());
				m.addActionListener(this);
				submenu.getChildren().add(m);
			}
			
			bean.setModel(model);
			
			bean.handleExRule(DBExRules.loadExRules(itemValue));
		}
		else if(bean.getModMan() == null) {
			
			bean.setModMan(itemValue);
			bean.setModuleList((DBModule.loadModulesByManTitle((itemValue))));
			
			MenuModel backModel = new DefaultMenuModel();
			Submenu backSubmenu = new Submenu();
			backSubmenu.setLabel("Zur\u00FCck zu:");
			MenuItem exRules = new MenuItem();
			exRules.setAjax(true);
			exRules.setUpdate(RedakteurBean.UPDATE_AJAX);
			exRules.setValue(RedakteurBean.PRUEFORDNUNG);
			exRules.addActionListener(back);
			backSubmenu.getChildren().add(exRules);
			MenuItem modMan = new MenuItem();
			modMan.setAjax(true);
			modMan.setUpdate(RedakteurBean.UPDATE_AJAX);
			modMan.setValue(RedakteurBean.MODMANUAL);
			modMan.addActionListener(back);
			backSubmenu.getChildren().add(modMan);
			backModel.addSubmenu(backSubmenu);
			bean.setBackModel(backModel);
			
			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(RedakteurBean.MODULE);
			model.addSubmenu(submenu);
			
			for(int i = 0; i < bean.getModuleList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate(RedakteurBean.UPDATE_AJAX);
				m.setValue(bean.getModuleList().get(i).getModTitle());
				m.addActionListener(this);
				submenu.getChildren().add(m);
			}
			
			bean.setModel(model);
			
			bean.handleModManual(DBModManual.loadModManual(itemValue));
		}
		else if(bean.getModule() == null) {
			
			bean.setModule(itemValue);
			bean.setSubjectList(((DBSubject.loadSubject(0, itemValue))));
			
			MenuModel backModel = new DefaultMenuModel();
			Submenu backSubmenu = new Submenu();
			backSubmenu.setLabel("Zur\u00FCck zu:");
			MenuItem exRules = new MenuItem();
			exRules.setAjax(true);
			exRules.setUpdate(RedakteurBean.UPDATE_AJAX);
			exRules.setValue(RedakteurBean.PRUEFORDNUNG);
			exRules.addActionListener(back);
			backSubmenu.getChildren().add(exRules);
			MenuItem modMan = new MenuItem();
			modMan.setAjax(true);
			modMan.setUpdate(RedakteurBean.UPDATE_AJAX);
			modMan.setValue(RedakteurBean.MODMANUAL);
			modMan.addActionListener(back);
			backSubmenu.getChildren().add(modMan);
			MenuItem module = new MenuItem();
			module.setAjax(true);
			module.setUpdate(RedakteurBean.UPDATE_AJAX);
			module.setValue(RedakteurBean.MODULE);
			module.addActionListener(back);
			backSubmenu.getChildren().add(module);
			backModel.addSubmenu(backSubmenu);
			bean.setBackModel(backModel);
			
			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(RedakteurBean.FAECHER);
			model.addSubmenu(submenu);
			
			for(int i = 0; i < bean.getSubjectList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate(RedakteurBean.UPDATE_AJAX);
				m.setValue(bean.getSubjectList().get(i).getSubTitle());
				m.addActionListener(this);
				submenu.getChildren().add(m);
			}
			
			bean.setModel(model);
			
			bean.handleModule(DBModule.loadModule(itemValue));
		}
		else {
			MenuModel backModel = new DefaultMenuModel();
			Submenu backSubmenu = new Submenu();
			backSubmenu.setLabel("Zur\u00FCck zu:");
			MenuItem exRules = new MenuItem();
			exRules.setAjax(true);
			exRules.setUpdate(RedakteurBean.UPDATE_AJAX);
			exRules.setValue(RedakteurBean.PRUEFORDNUNG);
			exRules.addActionListener(back);
			backSubmenu.getChildren().add(exRules);
			MenuItem modMan = new MenuItem();
			modMan.setAjax(true);
			modMan.setUpdate(RedakteurBean.UPDATE_AJAX);
			modMan.setValue(RedakteurBean.MODMANUAL);
			modMan.addActionListener(back);
			backSubmenu.getChildren().add(modMan);
			MenuItem module = new MenuItem();
			module.setAjax(true);
			module.setUpdate(RedakteurBean.UPDATE_AJAX);
			module.setValue(RedakteurBean.MODULE);
			module.addActionListener(back);
			backSubmenu.getChildren().add(module);
			backModel.addSubmenu(backSubmenu);
			bean.setBackModel(backModel);
			
			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(RedakteurBean.FAECHER);
			model.addSubmenu(submenu);
			
			for(int i = 0; i < bean.getSubjectList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate(RedakteurBean.UPDATE_AJAX);
				m.setValue(bean.getSubjectList().get(i).getSubTitle());
				m.addActionListener(this);
				submenu.getChildren().add(m);
			}
			
			bean.setModel(model);
			
			bean.handleSubject(DBSubject.loadSubject(0, itemValue, bean.getModule()));
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
