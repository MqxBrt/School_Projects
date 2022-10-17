package fr.umfds.GestionTER;
import java.util.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest 
{
	private Student student1 = new Student();
	private Student student2 = new Student(007,"James","Bond",null);
    private Subject subject1 = new Subject();
    private Subject subject2 = new Subject(1,"Hasbulla","Etude des chiffres",null,null);
    private Teacher teacher1 = new Teacher();
    private Teacher teacher2 = new Teacher(12,"Fungala","Kwaki",new ArrayList<Subject>());
    private Group group1 = new Group();
    private Group group2 = new Group(6,"FC Jean Crepe",null,new ArrayList<Student>());
	
	@Test
    public void testStudent() {
        assertEquals(student1.getID(),0);
        assertEquals(student1.getFirstName(),null);
        assertEquals(student1.getLastName(),null);
        assertEquals(student1.getGroup(),null);
        assertEquals(student1.toString(),"Etudiant ID '0' de prenom 'null' et nom 'null' du groupe 'null'");
        
        assertEquals(student2.getID(),7);
        assertEquals(student2.getFirstName(),"James");
        assertEquals(student2.getLastName(),"Bond");
        assertEquals(student2.getGroup(),null);
        assertEquals(student2.toString(),"Etudiant ID '7' de prenom 'James' et nom 'Bond' du groupe 'null'");
        
        student1.setID(2190);
        student1.setFirstName("Pascal");
        student1.setLastName("Boud");
        student1.changeGroup(group1);
        
        assertEquals(student1.getID(),2190);
        assertEquals(student1.getFirstName(),"Pascal");
        assertEquals(student1.getLastName(),"Boud");
        assertEquals(student1.getGroup(),group1);
        assertEquals(student1.toString(),"Etudiant ID '2190' de prenom 'Pascal' et nom 'Boud' du groupe '0'");
        
        student1.leaveGroup();
        
        assertEquals(student1.getGroup(),null);
    }
	
	@Test
	public void testSubject() {
		assertEquals(subject1.getID(),0);
		assertEquals(subject1.getTitle(),null);
		assertEquals(subject1.getSummary(),null);
		assertEquals(subject1.getTeacher(),null);
		assertEquals(subject1.getGroup(),null);
		assertEquals(subject1.toString(),"Sujet ID '0' de titre 'null' avec comme résumé 'null' comme enseignant 'null' et du groupe 'null'");
		
		assertEquals(subject2.getID(),1);
		assertEquals(subject2.getTitle(),"Hasbulla");
		assertEquals(subject2.getSummary(),"Etude des chiffres");
		assertEquals(subject2.getTeacher(),null);
		assertEquals(subject2.getGroup(),null);
		assertEquals(subject2.toString(),"Sujet ID '1' de titre 'Hasbulla' avec comme résumé 'Etude des chiffres' comme enseignant 'null' et du groupe 'null'");
		
		subject1.setID(118);
		subject1.setTitle("Pollution de l'eau");
		subject1.setSummary("TPE éclaté");
		subject1.changeTeacher(teacher1);
		subject1.changeGroup(group1);
		
		assertEquals(subject1.getID(),118);
		assertEquals(subject1.getTitle(),"Pollution de l'eau");
		assertEquals(subject1.getSummary(),"TPE éclaté");
		assertEquals(subject1.getTeacher(),teacher1);
		assertEquals(subject1.getGroup(),group1);
		assertEquals(subject1.toString(),"Sujet ID '118' de titre 'Pollution de l'eau' avec comme résumé 'TPE éclaté' comme enseignant '0' et du groupe '0'");
		
		subject1.removeTeacher();
		subject1.removeGroup();
		
		assertEquals(subject1.getTeacher(),null);
		assertEquals(subject1.getGroup(),null);
	}
	
	@Test
	public void testTeacher() {
		assertEquals(teacher1.getID(),0);
		assertEquals(teacher1.getFirstName(),null);
		assertEquals(teacher1.getLastName(),null);
		assertTrue(teacher1.getSubjects().isEmpty());
		assertEquals(teacher1.toString(),"Professeur ID '0' de prenom 'null' et nom 'null' avec les sujets []");
		
		assertEquals(teacher2.getID(),12);
		assertEquals(teacher2.getFirstName(),"Fungala");
		assertEquals(teacher2.getLastName(),"Kwaki");
		assertTrue(teacher2.getSubjects().isEmpty());
		assertEquals(teacher2.toString(),"Professeur ID '12' de prenom 'Fungala' et nom 'Kwaki' avec les sujets []");
		
		teacher1.setID(218);
		teacher1.setFirstName("Abdoul");
		teacher1.setLastName("Ibra");
		teacher1.addSubject(subject1);
		
		assertEquals(teacher1.getID(),218);
		assertEquals(teacher1.getFirstName(),"Abdoul");
		assertEquals(teacher1.getLastName(),"Ibra");
		assertEquals(teacher1.getSubjects().get(teacher1.getSubjects().size()-1),subject1);
		assertEquals(teacher1.toString(),"Professeur ID '218' de prenom 'Abdoul' et nom 'Ibra' avec les sujets [0]");
		
		teacher1.removeSubject(subject1);
		
		assertTrue(teacher1.getSubjects().isEmpty());
	}
	
	@Test
	public void testGroup() {
		assertEquals(group1.getID(),0);
		assertEquals(group1.getName(),null);
		assertEquals(group1.getSubject(),null);
		assertTrue(group1.getStudents().isEmpty());
		assertEquals(group1.toString(),"Groupe ID '0' de nom 'null' sur le sujet 'null' des etudiants []");
		
		assertEquals(group2.getID(),6);
		assertEquals(group2.getName(),"FC Jean Crepe");
		assertEquals(group2.getSubject(),null);
		assertTrue(group2.getStudents().isEmpty());
		assertEquals(group2.toString(),"Groupe ID '6' de nom 'FC Jean Crepe' sur le sujet 'null' des etudiants []");
		
		group1.setID(712);
		group1.setName("CRAB");
		group1.changeSubject(subject1);
		group1.addStudent(student1);

		assertEquals(group1.getID(),712);
		assertEquals(group1.getName(),"CRAB");
		assertEquals(group1.getSubject(),subject1);
		assertEquals(group1.getStudents().get(group1.getStudents().size()-1),student1);
		assertEquals(group1.toString(),"Groupe ID '712' de nom 'CRAB' sur le sujet '0' des etudiants [0]");
		
		group1.removeSubject();
		group1.removeStudent(student1);
		
		assertEquals(group1.getSubject(),null);
		assertTrue(group1.getStudents().isEmpty());
	}
	
	@Test
	public void testMain() {
		
	}
}
