package fr.umfds.GestionTER;
import java.util.*;

public class Group {
	private int ID;
	private String Name;
	private Subject Subject;
	private ArrayList<Student> Students;
	
	//Constructors
	public Group() {
		this.ID = 0;
		this.Name = null;
		this.Subject = null;
		this.Students = new ArrayList<Student>();
	}
	public Group(int ID, String Name, Subject Subject, ArrayList<Student> Students) {
		this.ID = ID;
		this.Name = Name;
		this.Subject = Subject;
		this.Students = Students;
	}
	
	//Getters
	public int getID() {
		return this.ID;
	}
	public String getName() {
		return this.Name;
	}
	public Subject getSubject() {
		return this.Subject;
	}
	public ArrayList<Student> getStudents() {
		return this.Students;
	}
	
	//Setters
	public void setID(int ID) {
		this.ID = ID;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public void setSubject(Subject Subject) {
		this.Subject = Subject;
	}
	public void setStudents(ArrayList<Student> Students) {
		this.Students = Students;
	}
	
	//Methods
	public String toString() {
		return "Groupe ID '" + this.getID() + "' de nom '" + this.getName() + "' sur le sujet '" + this.toStringSubject() + "' des etudiants [" + this.toStringStudents() + "]";
	}
	public String toStringSubject() {
		String rep = "null";
		if (this.getSubject() == null) {
			return rep;
		}
		else {
			rep = Integer.toString(this.getSubject().getID());
			return rep;
		}
	}
	public String toStringStudents() {
		int i = 0;
		String rep = "";
		for (i=0;i<this.getStudents().size();i++) {
			if (i == 0) {
				rep = rep + this.getStudents().get(i).getID();
			}
			else {
				rep = rep + " " + this.getStudents().get(i).getID();
			}
		}
		return rep;
	}
	public void addStudent(Student NewStudent) {
		if ((!this.getStudents().contains(NewStudent)) && (NewStudent.getGroup() == null) && (this.getStudents().size() < 5)) {
			this.getStudents().add(NewStudent);
			NewStudent.setGroup(this);
			NewStudent.setGroupID(this.getID());
		}
	}
	public void removeStudent(Student ExStudent) {
		if (this.getStudents().contains(ExStudent)) {
			this.getStudents().remove(ExStudent);
			ExStudent.setGroup(null);
			ExStudent.setGroupID(0);
		}
	}
	public void addSubject(Subject NewSubject) {
		if (NewSubject.getGroup() == null) {
			this.setSubject(NewSubject);
			NewSubject.setGroup(this);
			NewSubject.setGroupID(this.getID());
		}
	}
	public void removeSubject() {
		if (this.getSubject() != null) {
			this.getSubject().setGroup(null);
			this.getSubject().setGroupID(0);
			this.setSubject(null);
			
		}
	}
	public void changeSubject(Subject NewSubject) {
		this.removeSubject();
		this.addSubject(NewSubject);
	}
}







