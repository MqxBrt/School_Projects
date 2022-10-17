package fr.umfds.GestionTER;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Subject {
	private int ID;
	private String Title;
	private String Summary;
	@JsonIgnore
	private Teacher Teacher;
	private int teacherID;
	@JsonIgnore
	private Group Group;
	private int groupID;
	
	//Constructors
	public Subject() {
		this.ID = 0;
		this.Title = null;
		this.Summary = null;
		this.Teacher = null;
		this.teacherID = 0;
		this.Group = null;
		this.groupID = 0;
	}
	public Subject(int ID, String Title, String Summary, Teacher Teacher, Group Group) {
		this.ID = ID;
		this.Title = Title;
		this.Summary = Summary;
		this.Teacher = Teacher;
		if (Teacher == null) {
			this.teacherID = 0;
		}
		else {
			this.teacherID = Teacher.getID();
		}
		this.Group = Group;
		if (Group == null) {
			this.groupID = 0;
		}
		else {
			this.groupID = Group.getID();
		}
	}
	
	//Getters
	public int getID() {
		return this.ID;
	}
	public String getTitle() {
		return this.Title;
	}
	public String getSummary() {
		return this.Summary;
	}
	@JsonIgnore
	public Teacher getTeacher() {
		return this.Teacher;
	}
	public int getTeacherID() {
		return this.teacherID;
	}
	@JsonIgnore
	public Group getGroup() {
		return this.Group;
	}
	public int getGroupID() {
		return this.groupID;
	}
	
	//Setters
	public void setID(int ID) {
		this.ID = ID;
	}
	public void setTitle(String Title) {
		this.Title = Title;
	}
	public void setSummary(String Summary) {
		this.Summary = Summary;
	}
	@JsonProperty
	public void setTeacher(Teacher Teacher) {
		this.Teacher = Teacher;
	}
	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}
	@JsonProperty
	public void setGroup(Group Group) {
		this.Group = Group;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	
	//Methods
	public String toString() {
		return "Sujet ID '" + this.getID() + "' de titre '" + this.getTitle() + "' avec comme résumé '" + this.getSummary() + "' comme enseignant '" + this.toStringTeacher() + "' et du groupe '" + this.toStringGroup() + "'";
	}
	public String toStringTeacher() {
		String rep = "null";
		if ((this.getTeacher() == null) && (this.getTeacherID() == 0)) {
			return rep;
		}
		else {
			rep = Integer.toString(this.getTeacherID());
			return rep;
		}
	}
	public String toStringGroup() {
		String rep = "null";
		if ((this.getGroup() == null) && (this.getGroupID() == 0)) {
			return rep;
		}
		else {
			rep = Integer.toString(this.getGroupID());
			return rep;
		}
	}
	public void addGroup(Group NewGroup) {
		this.setGroup(NewGroup);
		NewGroup.setSubject(this);
	}
	public void removeGroup() {
		if (this.getGroup() != null) {
			this.getGroup().setSubject(null);
			this.setGroup(null);
		}
	}
	public void changeGroup(Group NewGroup) {
		if (NewGroup.getSubject() == null) {
			this.removeGroup();
			this.addGroup(NewGroup);
		}
	}
	public void addTeacher(Teacher NewTeacher) {
		NewTeacher.addSubject(this);
	}
	public void removeTeacher() {
		if (this.getTeacher() != null) {
			this.getTeacher().removeSubject(this);
		}
	}
	public void changeTeacher(Teacher NewTeacher) {
		this.removeTeacher();
		this.addTeacher(NewTeacher);
	}
}





