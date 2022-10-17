package fr.umfds.TP2_Courriel;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runners.Parameterized;

public class TestsCourriel {
	private Courriel c;

	@BeforeEach
	public void init() {
		c = new Courriel();
	}

    @Parameterized.Parameters
    public static Stream<Arguments> badAdresses() {
        return Stream.of(
            Arguments.of("adresse", "Pas d'arobase"),
            Arguments.of("@eee.fr", "Rien avant l'arobase"),
            Arguments.of("aaa@aaaa.", "Pas de nom de domaine")
        );
    }
	
    @ParameterizedTest(name = "run with adress : {0}, problem: {1}")
    @MethodSource("badAdresses")
    public void testAdresses(String adress, String problem) {
        c.setAdresse(adress);
        EnvoiInvalide thrown = assertThrows(EnvoiInvalide.class, () -> {c.envoyer();});
        assertTrue(thrown.getMessage().equals("Adresse du destinataire incorrecte"));
    }

    //envoyer sans adresse
    @ParameterizedTest(name = "run with adress : {0}, problem: {1}")
    @MethodSource("badAdresses")
    public void sendWithoutAdr()
    {
        EnvoiInvalide thrown = assertThrows(EnvoiInvalide.class, () -> {c.envoyer();});
        assertTrue(thrown.getMessage().equals("Pas de destinataire"));
    }
    //envoyer avec adresse mal formée
    @ParameterizedTest(name = "run with adress : {0}, problem: {1}")
    @MethodSource("badAdresses")
    public void sendWithBadAdr1()
    {
        c.setAdresse("adresse");
        EnvoiInvalide thrown = assertThrows(EnvoiInvalide.class, () -> {c.envoyer();});
        assertTrue(thrown.getMessage().equals("Adresse du destinataire incorrecte"));
    }
    @ParameterizedTest(name = "run with adress : {0}, problem: {1}")
    @MethodSource("badAdresses")
    public void sendWithBadAdr2()
    {
        c.setAdresse("@eee.fr");
        EnvoiInvalide thrown = assertThrows(EnvoiInvalide.class, () -> {c.envoyer();});
        assertTrue(thrown.getMessage().equals("Adresse du destinataire incorrecte"));
    }
    @ParameterizedTest(name = "run with adress : {0}, problem: {1}")
    @MethodSource("badAdresses")
    public void sendWithBadAdr3()
    {
        c.setAdresse("aaa@aaaa.");
        EnvoiInvalide thrown = assertThrows(EnvoiInvalide.class, () -> {c.envoyer();});
        assertTrue(thrown.getMessage().equals("Adresse du destinataire incorrecte"));
    }
    //envoyer sans titre (adr bien formée)
    @ParameterizedTest(name = "run with adress : {0}, problem: {1}")
    @MethodSource("badAdresses")
    public void sendWithoutTitle()
    {
        c.setAdresse("adresse@endroit.domaine");
        EnvoiInvalide thrown = assertThrows(EnvoiInvalide.class, () -> {c.envoyer();});
        assertTrue(thrown.getMessage().equals("Pas de titre"));
    }
    //envoyer avec titre, adr, avec piece jointe, mess contenant "join"
    @ParameterizedTest(name = "run with adress : {0}, problem: {1}")
    @MethodSource("badAdresses")
    public void sendWithoutJoint1()
    {
        c.setAdresse("adresse@endroit.domaine");
        c.setTitre("titre generique");
        c.setCorps("ceci est un message qui contient joint dedans");
        EnvoiInvalide thrown = assertThrows(EnvoiInvalide.class, () -> {c.envoyer();});
        assertTrue(thrown.getMessage().equals("Pas de piece jointe"));
    }
    @ParameterizedTest(name = "run with adress : {0}, problem: {1}")
    @MethodSource("badAdresses")
    public void sendWithoutJoint2()
    {
        c.setAdresse("adresse@endroit.domaine");
        c.setTitre("titre generique");
        c.setCorps("ceci est un message qui contient jointe dedans");
        EnvoiInvalide thrown = assertThrows(EnvoiInvalide.class, () -> {c.envoyer();});
        assertTrue(thrown.getMessage().equals("Pas de piece jointe"));
    }
    @ParameterizedTest(name = "run with adress : {0}, problem: {1}")
    @MethodSource("badAdresses")
    public void sendWithoutJoint3()
    {
        c.setAdresse("adresse@endroit.domaine");
        c.setTitre("titre generique");
        c.setCorps("ceci est un message qui contient une PJ à l'intérieur dedans");
        EnvoiInvalide thrown = assertThrows(EnvoiInvalide.class, () -> {c.envoyer();});
        assertTrue(thrown.getMessage().equals("Pas de piece jointe"));
    }
    //un test avec tout qui marche correctement
    @ParameterizedTest(name = "run with adress : {0}, problem: {1}")
    @MethodSource("badAdresses")
    public void sendWithEverythingCorrect() {
        c.setAdresse("adresse@eee.domaine");
        c.setTitre("titre generique");
        c.setCorps("ceci est un message qui contient une PJ à l'intérieur dedans");
        c.addJoint("/c/Robin/mesDocuments/FichierPhenix.phx");
        try {
        	c.envoyer();
        } catch (Exception e) {
        	fail(e);
        }
    }
}