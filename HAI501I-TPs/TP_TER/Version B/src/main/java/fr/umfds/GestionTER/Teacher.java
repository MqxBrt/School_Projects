package fr.umfds.GestionTER;
import java.io.File;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Teacher {
	private int ID;
	private String FirstName;
	private String LastName;
	private ArrayList<Subject> Subjects = new ArrayList<Subject>();
	
	//Constructors
	public Teacher() {
		this.ID = 0;
		this.FirstName = null;
		this.LastName = null;
		this.Subjects = new ArrayList<Subject>();
	}
	public Teacher(int ID, String FirstName, String LastName, ArrayList<Subject> Subjects) {
		this.ID = ID;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Subjects = Subjects;
	}
	
	//Getters
	public int getID() {
		return this.ID;
	}
	public String getFirstName() {
		return this.FirstName;
	}
	public String getLastName() {
		return this.LastName;
	}
	public ArrayList<Subject> getSubjects() {
		return this.Subjects;
	}
	
	//Setters
	public void setID(int ID) {
		this.ID = ID;
	}
	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}
	public void setLastName(String LastName) {
		this.LastName = LastName;
	}
	public void setSubjects(ArrayList<Subject> Subjects) {
		this.Subjects = Subjects;
	}
	
	//Methods
	public String toString() {
		return "Professeur ID '" + this.getID() + "' de prenom '" + this.getFirstName() + "' et nom '" + this.getLastName() + "' avec les sujets [" + this.toStringSubjects() + "]";
	}
	public String toStringSubjects() {
		int i = 0;
		String rep = "";
		for (i=0;i<this.getSubjects().size();i++) {
			if (i == 0) {
				rep = rep + this.getSubjects().get(i).getID();
			}
			else {
				rep = rep + " " + this.getSubjects().get(i).getID();
			}
		}
		return rep;
	}
	public void addSubject(Subject NewSubject) {
		if ((!this.getSubjects().contains(NewSubject)) && (NewSubject.getTeacher() == null)) {
			this.getSubjects().add(NewSubject);
			NewSubject.setTeacher(this);
			NewSubject.setTeacherID(this.getID());
		}
	}
	public void removeSubject(Subject ExSubject) {
		if (this.getSubjects().contains(ExSubject)) {
			this.getSubjects().remove(ExSubject);
			ExSubject.setTeacher(null);
			ExSubject.setTeacherID(0);
		}
	}
}









