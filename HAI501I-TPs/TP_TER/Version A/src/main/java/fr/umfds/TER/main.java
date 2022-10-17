package fr.umfds.GestionTER;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class main {
	public static void main(String[] args) {
		Teacher teacher1 = new Teacher(7,"Sylvain","Daudé",new ArrayList<Subject>());
		Student student1 = new Student(1,"Maxime","B",null);
		Student student2 = new Student(2,"Arnaud","C",null);
		Student student3 = new Student(3,"Gatien","H",null);
		Student student4 = new Student(4,"Adam","S",null);
		Group group1 = new Group(9,"Les Gnomes",null,new ArrayList<Student>());
		Group group2 = new Group(2,"Riri",null,new ArrayList<Student>());
		Group group3 = new Group(3,"Fifi",null,new ArrayList<Student>());
		Group group4 = new Group(4,"Loulou",null,new ArrayList<Student>());
		Group group5 = new Group(5,"Picsou",null,new ArrayList<Student>());
		Subject subject1 = new Subject(5,"Etude de Matis.B","Disparition de Matis à la Fac",null,null);
		ArrayList<Group> listGroups = new ArrayList<Group>();
		for (int i=0; i<7; i++) {
			listGroups.add(group1);
			listGroups.add(group2);
			listGroups.add(group3);
			listGroups.add(group4);
			//listGroups.add(group5);
		}
		
		student1.joinGroup(group1);
		student2.joinGroup(group1);
		student3.joinGroup(group1);
		student4.joinGroup(group1);
		teacher1.addSubject(subject1);
		group1.changeSubject(subject1);
		
		groupIntoJSON(group1, "group1");
		teacherIntoJSON(teacher1, "teacher1");
		subjectIntoJSON(subject1, "subject1");
		studentIntoJSON(student1, "student1");
		studentIntoJSON(student2, "student2");
		studentIntoJSON(student3, "student3");
		studentIntoJSON(student4, "student4");
		
		groupOutOfJSON("group1");
		teacherOutOfJSON("teacher1");
		subjectOutOfJSON("subject1");
		studentOutOfJSON("student1");
		studentOutOfJSON("student2");
		studentOutOfJSON("student3");
		studentOutOfJSON("student4");
		
		ArrayList<ArrayList<Group>> unPlanning = randomPlanning(listGroups);
		planningToString(unPlanning);
		soutenanceToString(group1, unPlanning);
		soutenanceToString(group5, unPlanning);
		
		System.out.println("Eureka !");
	}
	
	public static void teacherIntoJSON(Teacher teacher, String title) {
		ObjectMapper OM = new ObjectMapper();
		try {
			OM.writeValue(new File("target/"+title+".json"), teacher);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void subjectIntoJSON(Subject subject, String title) {
		ObjectMapper OM = new ObjectMapper();
		try {
			OM.writeValue(new File("target/"+title+".json"), subject);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void groupIntoJSON(Group group, String title) {
		ObjectMapper OM = new ObjectMapper();
		try {
			OM.writeValue(new File("target/"+title+".json"), group);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void studentIntoJSON(Student student, String title) {
		ObjectMapper OM = new ObjectMapper();
		try {
			OM.writeValue(new File("target/"+title+".json"), student);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Teacher teacherOutOfJSON(String title) {
		ObjectMapper OM = new ObjectMapper();
		try {
			Teacher teacher = OM.readValue(Paths.get("target/"+title+".json").toFile(), Teacher.class);
			System.out.println(teacher.toString());
			return teacher;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Subject subjectOutOfJSON(String title) {
		ObjectMapper OM = new ObjectMapper();
		try {
			Subject subject = OM.readValue(Paths.get("target/"+title+".json").toFile(), Subject.class);
			System.out.println(subject.toString());
			return subject;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Group groupOutOfJSON(String title) {
		ObjectMapper OM = new ObjectMapper();
		try {
			Group group = OM.readValue(Paths.get("target/"+title+".json").toFile(), Group.class);
			System.out.println(group.toString());
			return group;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Student studentOutOfJSON(String title) {
		ObjectMapper OM = new ObjectMapper();
		try {
			Student student = OM.readValue(Paths.get("target/"+title+".json").toFile(), Student.class);
			System.out.println(student.toString());
			return student;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<ArrayList<Group>> randomPlanning(ArrayList<Group> groups) {
		Collections.shuffle(groups);
		ArrayList<ArrayList<Group>> planning = new ArrayList<ArrayList<Group>>();
		Group nullGroup = new Group();
		int k = 0;
		for (int i=0; i<5; i++) {
			planning.add(new ArrayList<Group>());
			for (int j=0; j<7; j++) {
				if (k>=groups.size()) {
					planning.get(i).add(nullGroup);
				}
				else {
					planning.get(i).add(groups.get(k));
					k++;
				}
			}
		}
		return planning;
	}
	public static void planningToString(ArrayList<ArrayList<Group>> planning) {
		Group tempGroup = new Group();
		ArrayList<String> days = new ArrayList<String>();
		days.add("09-10 |  ");
		days.add("10-11 |  ");
		days.add("11-12 |  ");
		days.add("13-14 |  ");
		days.add("14-15 |  ");
		days.add("15-16 |  ");
		days.add("16-17 |  ");
		System.out.println("      |  L M M J V");
		System.out.println("______|___________");
		for (int i=0; i<7; i++) {
			System.out.print(days.get(i));
			for (int j=0; j<5; j++) {
				tempGroup = planning.get(j).get(i);
				System.out.print(tempGroup.getID());
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}
	public static void soutenanceToString(Group group, ArrayList<ArrayList<Group>> planning) {
		int i = 0;
		int j = 0;
		String day = null;
		String time = null;
		ArrayList<String> days = new ArrayList<String>();
		ArrayList<String> times = new ArrayList<String>();
		days.add("lundi");
		days.add("mardi");
		days.add("mercredi");
		days.add("jeudi");
		days.add("vendredi");
		times.add("9 à 10");
		times.add("10 à 11");
		times.add("11 à 12");
		times.add("13 à 14");
		times.add("14 à 15");
		times.add("15 à 16");
		times.add("16 à 17");
		boolean found = false;
		while (!found && i<5) {
			j = 0;
			while (!found && j<7) {
				if (planning.get(i).get(j) == group) {
					found  = true;
				}
				j++;
			}
			i++;
		}
		String soutenance = null;
		if (!found) {
			soutenance = "Le groupe \"" + group.getID() + " : " + group.getName() + "\" n'a pas de soutenance prévue cette semaine.";
		}
		else {
			day = days.get(i-1);
			time = times.get(j-1); 
			soutenance = "Le groupe \"" + group.getID() + " : " + group.getName() + "\" a une soutenance prévue le " + day + " de " + time + ".";
		}
		System.out.println(soutenance);
		
	}
}





/*public static class Foo {
public String s;
public Foo(String s) {
	this.s = s;
}
public String toString() {
	return "Foo value : " + this.s;
}
}
public static void main(String[] args) {
System.out.println("Hello");
main.Foo a = new main.Foo("A");
main.Foo b = new main.Foo("B");
ArrayList<Foo> l = new ArrayList<Foo>();
l.add(a);
l.add(b);
System.out.println(l);
}*/