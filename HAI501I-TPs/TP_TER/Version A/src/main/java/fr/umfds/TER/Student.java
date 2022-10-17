package fr.umfds.TER;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {
	private int ID;
	private String FirstName;
	private String LastName;
	@JsonIgnore
	private Group Group;
	private int groupID;
	
	//Constructors
	public Student() {
		this.ID = 0;
		this.FirstName = null;
		this.LastName = null;
		this.Group = null;
		this.groupID = 0;
	}
	public Student(int ID, String FirstName, String LastName, Group Group) {
		this.ID = ID;
		this.FirstName = FirstName;
		this.LastName = LastName;
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
	public String getFirstName() {
		return this.FirstName;
	}
	public String getLastName() {
		return this.LastName;
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
	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}
	public void setLastName(String LastName) {
		this.LastName = LastName;
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
		return "Etudiant ID '" + this.getID() + "' de prenom '" + this.getFirstName() + "' et nom '" + this.getLastName() + "' du groupe '" + this.toStringGroup() + "'";
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
	public void joinGroup(Group NewGroup) {
		NewGroup.addStudent(this);
	}
	public void leaveGroup() {
		if (this.getGroup() != null) {
			this.getGroup().removeStudent(this);
		}
	}
	public void changeGroup(Group NewGroup) {
		this.leaveGroup();
		this.joinGroup(NewGroup);
	}
}





