package server;

import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import ctrl.DBField;
import ctrl.DBSubject;

import data.Field;
import data.Subject;

@ManagedBean(name = "DezernatBean")
@SessionScoped
public class DezernatBean {
	private List<Subject> subjectList;
	private List<Field> fieldList;

	private HashMap<Subject, List<Field>> Map;
	private Subject selected;

	public DezernatBean() {

		subjectList = DBSubject.loadSubjectsforDezernat();
		Map = new HashMap<Subject, List<Field>>();

		for (Subject s : subjectList) {
			s.getSubTitle();
			if(!DBField.loadFieldforDezernat(s.getSubTitle()).isEmpty()){
				fieldList = DBField.loadFieldforDezernat(s.getSubTitle());
			}
			
			Map.put(s, fieldList);
			System.out.println(fieldList.toString());
		}
		System.out.println(subjectList.toString());
	}

	public void loadFields(ActionEvent e) {
		System.out.println("selected");
		
		if(selected!=null){
			System.out.println(selected.getSubTitle());
			fieldList=DBField.loadFieldforDezernat(selected.getSubTitle());
			for(int i=0; i<10;i++)
			fieldList.add(new Field(""+i, i, ""+i, ""+i, "Usability-Gründen sondern aus reinem Marketing-Gedanken eingeführt hat und versucht durchzudrücken.Wenn dies eine technische Unmöglichkeit oder ein enormer Aufwand gewesen wäre die Dualität einzubauen, würde ja keiner etwas sagen, aber dass es auch anderes gegangen wäre zeigt ja der Registry-Hack in der Win8-Beta."+(long)i*i*i*i*i*i*i+"\r\n"+i*i*i));
		}
	}
	
	public void acceptChanges(ActionEvent e){
		System.out.println(selected.getSubTitle()+" is accepted!");
		DBSubject.updateSubjectAck(true,selected.getVersion(), selected.getSubTitle(), selected.getModTitle());
		subjectList.remove(selected);
	}
	
	/**
	 * @return the fieldList
	 */
	public List<Field> getFieldList() {
		return fieldList;
	}

	/**
	 * @param fieldList
	 *            the fieldList to set
	 */
	public void setFieldList(List<Field> fieldList) {
		this.fieldList = fieldList;
	}

	/**
	 * @return the subjectList
	 */
	public List<Subject> getSubjectList() {
		return subjectList;
	}

	/**
	 * @param subjectList the subjectList to set
	 */
	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	/**
	 * @return the selected
	 */
	public Subject getSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(Subject selected) {
		this.selected = selected;
	}

}
