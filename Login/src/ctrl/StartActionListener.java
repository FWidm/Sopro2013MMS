package ctrl;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import data.Subject;
import db.DBExRules;
import db.DBModManual;
import db.DBModule;
import db.DBSubject;

import server.DownloadBean;
import server.StartBean;


public class StartActionListener implements ActionListener {
	
	public void refreshMenu(String itemValue) {
		
		StartActionListenerBack back = new StartActionListenerBack();
		
		StartBean bean = findBean("StartBean");
		
		if(bean.getExRules() == null) {
			bean.setExRules(itemValue);
			bean.setModManList(DBModManual.loadModManuals(itemValue));
			
			MenuModel backModel = new DefaultMenuModel();
			Submenu backSubmenu = new Submenu();
			backSubmenu.setLabel("Zur\u00FCck zu:");
			MenuItem exRules = new MenuItem();
			exRules.setAjax(true);
			exRules.setUpdate(StartBean.UPDATE_AJAX);
			exRules.setValue(StartBean.PRUEFORDNUNG);
			exRules.addActionListener(back);
			backSubmenu.getChildren().add(exRules);
			backModel.addSubmenu(backSubmenu);
			bean.setBackModel(backModel);
			
			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(StartBean.MODMANUAL);
			model.addSubmenu(submenu);
			
			for(int i = 0; i < bean.getModManList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate(StartBean.UPDATE_AJAX);
				m.setValue(bean.getModManList().get(i).getModManTitle());
				m.addActionListener(this);
				submenu.getChildren().add(m);
			}
			
			bean.setModel(model);
			
			bean.handleExRule(DBExRules.loadExRules(itemValue));
		}
		else if(bean.getModMan() == null) {
			
			bean.setModMan(itemValue);
			bean.setModuleList((DBModule.loadModulesByManTitle(bean.getExRules(), itemValue)));
			
			MenuModel backModel = new DefaultMenuModel();
			Submenu backSubmenu = new Submenu();
			backSubmenu.setLabel("Zur\u00FCck zu:");
			MenuItem exRules = new MenuItem();
			exRules.setAjax(true);
			exRules.setUpdate(StartBean.UPDATE_AJAX);
			exRules.setValue(StartBean.PRUEFORDNUNG);
			exRules.addActionListener(back);
			backSubmenu.getChildren().add(exRules);
			MenuItem modMan = new MenuItem();
			modMan.setAjax(true);
			modMan.setUpdate(StartBean.UPDATE_AJAX);
			modMan.setValue(StartBean.MODMANUAL);
			modMan.addActionListener(back);
			backSubmenu.getChildren().add(modMan);
			backModel.addSubmenu(backSubmenu);
			bean.setBackModel(backModel);
			
			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(StartBean.MODULE);
			model.addSubmenu(submenu);
			
			for(int i = 0; i < bean.getModuleList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate(StartBean.UPDATE_AJAX);
				m.setValue(bean.getModuleList().get(i).getModTitle());
				m.addActionListener(this);
				submenu.getChildren().add(m);
			}
			
			bean.setModel(model);
			
			bean.handleModManual(DBModManual.loadModManual(itemValue));
		}
		else if(bean.getModule() == null) {
			
			bean.setModule(itemValue);
			bean.setSubjectList(((DBSubject.loadSubjectListMaxVersion(itemValue))));
			
			MenuModel backModel = new DefaultMenuModel();
			Submenu backSubmenu = new Submenu();
			backSubmenu.setLabel("Zur\u00FCck zu:");
			MenuItem exRules = new MenuItem();
			exRules.setAjax(true);
			exRules.setUpdate(StartBean.UPDATE_AJAX);
			exRules.setValue(StartBean.PRUEFORDNUNG);
			exRules.addActionListener(back);
			backSubmenu.getChildren().add(exRules);
			MenuItem modMan = new MenuItem();
			modMan.setAjax(true);
			modMan.setUpdate(StartBean.UPDATE_AJAX);
			modMan.setValue(StartBean.MODMANUAL);
			modMan.addActionListener(back);
			backSubmenu.getChildren().add(modMan);
			MenuItem module = new MenuItem();
			module.setAjax(true);
			module.setUpdate(StartBean.UPDATE_AJAX);
			module.setValue(StartBean.MODULE);
			module.addActionListener(back);
			backSubmenu.getChildren().add(module);
			backModel.addSubmenu(backSubmenu);
			bean.setBackModel(backModel);
			
			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(StartBean.FAECHER);
			model.addSubmenu(submenu);
			
			for(int i = 0; i < bean.getSubjectList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate(StartBean.UPDATE_AJAX);
				m.setValue(bean.getSubjectList().get(i).getSubTitle());
				m.addActionListener(this);
				submenu.getChildren().add(m);
			}
			
			bean.setModel(model);
			
			bean.handleModule(DBModule.loadModule(itemValue, bean.getModMan()));
		}
		else {
			MenuModel backModel = new DefaultMenuModel();
			Submenu backSubmenu = new Submenu();
			backSubmenu.setLabel("Zur\u00FCck zu:");
			MenuItem exRules = new MenuItem();
			exRules.setAjax(true);
			exRules.setUpdate(StartBean.UPDATE_AJAX);
			exRules.setValue(StartBean.PRUEFORDNUNG);
			exRules.addActionListener(back);
			backSubmenu.getChildren().add(exRules);
			MenuItem modMan = new MenuItem();
			modMan.setAjax(true);
			modMan.setUpdate(StartBean.UPDATE_AJAX);
			modMan.setValue(StartBean.MODMANUAL);
			modMan.addActionListener(back);
			backSubmenu.getChildren().add(modMan);
			MenuItem module = new MenuItem();
			module.setAjax(true);
			module.setUpdate(StartBean.UPDATE_AJAX);
			module.setValue(StartBean.MODULE);
			module.addActionListener(back);
			backSubmenu.getChildren().add(module);
			backModel.addSubmenu(backSubmenu);
			bean.setBackModel(backModel);
			
			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(StartBean.FAECHER);
			model.addSubmenu(submenu);
			
			for(int i = 0; i < bean.getSubjectList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate(StartBean.UPDATE_AJAX);
				m.setValue(bean.getSubjectList().get(i).getSubTitle());
				m.addActionListener(this);
				submenu.getChildren().add(m);
			}
			
			bean.setModel(model);
			
			Subject sub = DBSubject.loadSubjectMaxVersion(itemValue, bean.getModule());
			
			//for PDFBox
			DownloadBean download = findBean("DownloadBean");
			download.setDownloadSub(sub);
			
			bean.handleSubject(sub);
			
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
