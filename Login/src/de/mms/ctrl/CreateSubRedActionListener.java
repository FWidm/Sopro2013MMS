package de.mms.ctrl;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import de.mms.db.DBExRules;
import de.mms.db.DBModManual;
import de.mms.db.DBModule;
import de.mms.server.CreateSubRedBean;


public class CreateSubRedActionListener implements ActionListener {

	public void refreshMenu(String itemValue) {

		CreateSubRedActionListenerBack back = new CreateSubRedActionListenerBack();

		CreateSubRedBean bean = findBean("CreateSubRedBean");

		if (bean.getExRules() == null) {
			bean.setExRules(itemValue);
			bean.setModManList(DBModManual.loadModManuals(itemValue));

			MenuModel backModel = new DefaultMenuModel();
			Submenu backSubmenu = new Submenu();
			backSubmenu.setLabel("Zur\u00FCck zu:");
			MenuItem exRules = new MenuItem();
			exRules.setAjax(true);
			exRules.setUpdate(CreateSubRedBean.UPDATE_AJAX);
			exRules.setValue(CreateSubRedBean.PRUEFORDNUNG);
			exRules.addActionListener(back);
			backSubmenu.getChildren().add(exRules);
			backModel.addSubmenu(backSubmenu);
			bean.setBackModel(backModel);

			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(CreateSubRedBean.MODMANUAL);
			model.addSubmenu(submenu);

			for (int i = 0; i < bean.getModManList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate(CreateSubRedBean.UPDATE_AJAX);
				m.setValue(bean.getModManList().get(i).getModManTitle());
				m.addActionListener(this);
				submenu.getChildren().add(m);
			}

			bean.setModel(model);

			bean.handleExRule(DBExRules.loadExRules(itemValue));
		} else if (bean.getModMan() == null) {

			bean.setModMan(itemValue);
			bean.setModuleList((DBModule.loadModulesByManTitle(
					bean.getExRules(), itemValue)));

			MenuModel backModel = new DefaultMenuModel();
			Submenu backSubmenu = new Submenu();
			backSubmenu.setLabel("Zur\u00FCck zu:");
			MenuItem exRules = new MenuItem();
			exRules.setAjax(true);
			exRules.setUpdate(CreateSubRedBean.UPDATE_AJAX);
			exRules.setValue(CreateSubRedBean.PRUEFORDNUNG);
			exRules.addActionListener(back);
			backSubmenu.getChildren().add(exRules);
			MenuItem modMan = new MenuItem();
			modMan.setAjax(true);
			modMan.setUpdate(CreateSubRedBean.UPDATE_AJAX);
			modMan.setValue(CreateSubRedBean.MODMANUAL);
			modMan.addActionListener(back);
			backSubmenu.getChildren().add(modMan);
			backModel.addSubmenu(backSubmenu);
			bean.setBackModel(backModel);

			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(CreateSubRedBean.MODULE);
			model.addSubmenu(submenu);

			for (int i = 0; i < bean.getModuleList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate(CreateSubRedBean.UPDATE_AJAX);
				m.setValue(bean.getModuleList().get(i).getModTitle());
				m.addActionListener(this);
				submenu.getChildren().add(m);
			}

			bean.setModel(model);

			bean.handleModManual(DBModManual.loadModManual(itemValue));
		} else {

			bean.setModule(itemValue);

			MenuModel backModel = new DefaultMenuModel();
			Submenu backSubmenu = new Submenu();
			backSubmenu.setLabel("Zur\u00FCck zu:");
			MenuItem exRules = new MenuItem();
			exRules.setAjax(true);
			exRules.setUpdate(CreateSubRedBean.UPDATE_AJAX);
			exRules.setValue(CreateSubRedBean.PRUEFORDNUNG);
			exRules.addActionListener(back);
			backSubmenu.getChildren().add(exRules);
			MenuItem modMan = new MenuItem();
			modMan.setAjax(true);
			modMan.setUpdate(CreateSubRedBean.UPDATE_AJAX);
			modMan.setValue(CreateSubRedBean.MODMANUAL);
			modMan.addActionListener(back);
			backSubmenu.getChildren().add(modMan);
			backModel.addSubmenu(backSubmenu);
			bean.setBackModel(backModel);

			MenuModel model = new DefaultMenuModel();
			Submenu submenu = new Submenu();
			submenu.setLabel(CreateSubRedBean.MODULE);
			model.addSubmenu(submenu);

			for (int i = 0; i < bean.getModuleList().size(); i++) {
				MenuItem m = new MenuItem();
				m.setAjax(true);
				m.setUpdate(CreateSubRedBean.UPDATE_AJAX);
				m.setValue(bean.getModuleList().get(i).getModTitle());
				m.addActionListener(this);
				submenu.getChildren().add(m);
			}

			bean.setModel(model);

			bean.handleModule(DBModule.loadModule(itemValue, bean.getModMan()));
		}
	}

	@Override
	public void processAction(ActionEvent arg0) throws AbortProcessingException {
		// TODO Auto-generated method stub
		refreshMenu((String) ((MenuItem) arg0.getSource()).getValue());
	}

	@SuppressWarnings("unchecked")
	public static <T> T findBean(String beanName) {
		FacesContext context = FacesContext.getCurrentInstance();
		return (T) context.getApplication().evaluateExpressionGet(context,
				"#{" + beanName + "}", Object.class);
	}

}
