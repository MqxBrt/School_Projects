package fr.umfds.TER;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;


public class testsGroupeSujetTPNOTE {
    private Subject subject = new Subject(2,"Insectes","Communication chez les fourmis",null,null);
    private Group group = new Group(6,"Groupe des L3",null,new ArrayList<Student>());
    
    @Test
    public void testGroupeSujet() {
    	//On assigne le sujet "subject" au groupe "group"
    	group.changeSubject(subject);
    	
    	//On vérifie que l'attribut sujet de "group" est bien "subject"
    	assertEquals(group.getSubject(),subject);
    	//On vérifie que l'attribut groupe de "subject" est bien "group"
    	assertEquals(subject.getGroup(),group);
    }
}
