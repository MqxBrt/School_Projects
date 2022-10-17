package fr.umfds.TP4_Mocks;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
//import pour les test jUnit
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//imports pour Mockito
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.umfds.TP4_Mocks.BibliUtilities;
import fr.umfds.TP4_Mocks.Bibliothèque;
import fr.umfds.TP4_Mocks.IncorrectIsbnException;
import fr.umfds.TP4_Mocks.InterfaceGlobalBibliographyAccess;
import fr.umfds.TP4_Mocks.NoticeBibliographique;
import fr.umfds.TP4_Mocks.NoticeStatus;

@ExtendWith(MockitoExtension.class)
class TestBibliUtilities {
	//Déclaration des variables utilisées par les tests
	private NoticeBibliographique notice1;
	private NoticeBibliographique notice2;
	private NoticeBibliographique notice3;
	private NoticeBibliographique notice4;
	private NoticeBibliographique notice5;
	private NoticeBibliographique notice6;
	private NoticeBibliographique notice7;
	private NoticeBibliographique notice8;
	private NoticeBibliographique notice9;
	private NoticeBibliographique notice10;
	private Bibliothèque biblio;
	private ArrayList<NoticeBibliographique> liste;
	private ArrayList<NoticeBibliographique> mustReturn;

	@Mock
	Clock mockedClock;
	@Mock
	InterfaceGlobalBibliographyAccess global;
	@InjectMocks
	BibliUtilities bibliU = new BibliUtilities();

  //Initialisation des variables utilisées lors des tests, avant chacun d'entre eux
  @BeforeEach
  void initialisation() {
	  notice1 = new NoticeBibliographique("1", "Binaire et cailloux", "Jésus");
	  notice2 = new NoticeBibliographique("2", "Algèbre circulaire", "Jésus");
	  notice3 = new NoticeBibliographique("3", "Division Eulérienne", "Jésus");
	  notice4 = new NoticeBibliographique("4", "Algo Mamaché", "Jésus");
	  notice5 = new NoticeBibliographique("5", "Vie de luxe", "Jésus");
	  notice6 = new NoticeBibliographique("6", "667 en prison", "Jésus");
	  notice7 = new NoticeBibliographique("7", "La Résurrection", "Jésus");
	  notice8 = new NoticeBibliographique("8", "Piscine privée", "La Fontaine");
	  notice9 = new NoticeBibliographique("9", "Avion de course", "Jean Modulo");
	  notice10 = new NoticeBibliographique("10", "Go le rush", "Jean Modulo");
	  biblio = Bibliothèque.getInstance();
	  biblio.addNotice(notice4);
	  biblio.addNotice(notice5);
	  liste = new ArrayList<NoticeBibliographique>();
	  mustReturn = new ArrayList<NoticeBibliographique>();
  }

  //Test pour vérifier si chercherNoticesConnexes fonctionne pour 1 <= x <= 4
  @Test
  public void test2Connexes() {
	  liste.add(notice9);
	  liste.add(notice10);
	  mustReturn.add(notice10);
	  when(global.noticesDuMemeAuteurQue(notice9)).thenReturn(liste);
	  assertEquals(mustReturn,bibliU.chercherNoticesConnexes(notice9));
  }

  //Test pour vérifier que chercherNoticeConnexes s'arrête à 5 notices
  @Test
  public void test5Connexes() {
	  liste.add(notice1);
	  liste.add(notice2);
	  liste.add(notice3);
	  liste.add(notice4);
	  liste.add(notice5);
	  liste.add(notice6);
	  mustReturn.add(notice2);
	  mustReturn.add(notice3);
	  mustReturn.add(notice4);
	  mustReturn.add(notice5);
	  mustReturn.add(notice6);
	  when(global.noticesDuMemeAuteurQue(notice1)).thenReturn(liste);
	  assertEquals(mustReturn,bibliU.chercherNoticesConnexes(notice1));
  }

  //Test pour vérifier si chercherNoticesConnexes fonctionne pour x = 0
  @Test
  public void test0Connexes() {
	  when(global.noticesDuMemeAuteurQue(notice8)).thenReturn(liste);
	  assertEquals(mustReturn,bibliU.chercherNoticesConnexes(notice8));
  }

  //Test pour vérifier si l'exception est respectée
  @Test
  public void testAjoutInexistant() throws IncorrectIsbnException {
	  when(global.getNoticeFromIsbn("404")).thenThrow(IncorrectIsbnException.class);
	  assertThrows(AjoutImpossibleException.class, () -> {bibliU.ajoutNotice("404");});
	}

  //Test pour vérifier si la notice ajoutée est bien reconnue comme ajoutée récemment
  @Test 
  public void testAjoutCorrectNew() throws IncorrectIsbnException, AjoutImpossibleException {
	  when(global.getNoticeFromIsbn("7")).thenReturn(notice7);
	  assertEquals(NoticeStatus.newlyAdded,bibliU.ajoutNotice("7"));
  }

  //Test pour vérifier si la notice est reconnue comme inchangée
  @Test
  public void testAjoutCorrectNoChange() throws IncorrectIsbnException, AjoutImpossibleException {
	  when(global.getNoticeFromIsbn("5")).thenReturn(notice5);
	  assertEquals(NoticeStatus.nochange,bibliU.ajoutNotice("5"));
  }

  //Test pour vérifier si la notice est reconnue comme mise à jour
  @Test
  public void testAjoutCorrectUpdated() throws IncorrectIsbnException, AjoutImpossibleException {
	  notice4 = new NoticeBibliographique("4", "Algo Lgoth", "Jésus");
	  when(global.getNoticeFromIsbn("4")).thenReturn(notice4);
	  assertEquals(NoticeStatus.updated,bibliU.ajoutNotice("4"));
  }

  //Test pour vérifier si la méthode prevoirInventaire reconnait que le plus récent a moins d'un an
  @Test
  public void testPrevoirInventaireNoNeed() {
	  //on mock la clock a la date du 7 Décembre 2021
	  LocalDate date = LocalDate.of(2021, 12, 7);
	  Clock fixedClock = Clock.fixed(date.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
	  doReturn(fixedClock.instant()).when(mockedClock).instant();
	  doReturn(fixedClock.getZone()).when(mockedClock).getZone();
	  //on récupère la date du dernier inventaire (6 Décembre)
	  LocalDate lastInv = biblio.getLastInventaire();
	  bibliU.prevoirInventaire();
	  LocalDate newInv = biblio.getLastInventaire();
	  //le dernier inventaire à été effectué il y a moins d'un an, un nouvel inventaire n'est donc pas nécessaire
	  assertTrue(lastInv.equals(newInv));
  }
  
  @Test
  public void testPrevoirInventaireNeed() {  
	  //la clock est mockée au 25 décembre 2022
	  LocalDate date = LocalDate.of(2022, 12, 25);
	  Clock fixedClock = Clock.fixed(date.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
	  doReturn(fixedClock.instant()).when(mockedClock).instant();
	  doReturn(fixedClock.getZone()).when(mockedClock).getZone();
	  LocalDate lastInv = biblio.getLastInventaire();
	  bibliU.prevoirInventaire();
	  LocalDate newInv = biblio.getLastInventaire();
	  //le dernier inventaire ayant eu lieu il y a plus d'un an, il faut donc prévoir un nouvel inventaire
	  assertFalse(lastInv.equals(newInv));
  }
  
	@Mock
	NoticeBibliographique notice0;
	
  @Test
  public void testAjoutSpy() throws AjoutImpossibleException, IncorrectIsbnException {
	  //on crée un spy d'une biblio
	  Bibliothèque leSpy = Mockito.spy(biblio);
	  //on fais en sorte que lors du get de la notice 0, on renvoie la notice 0 (mockée)
	  when(global.getNoticeFromIsbn("0")).thenReturn(notice0);
	  assertEquals(leSpy.addNotice(notice0),NoticeStatus.newlyAdded);
	  bibliU.ajoutNotice("0");
	  //on teste si la notice mockée a bien été ajoutée
	  assertEquals(leSpy.addNotice(notice0),NoticeStatus.nochange);
  }
  
  @Test
  public void testSpyDetourner() throws AjoutImpossibleException, IncorrectIsbnException {
	  //on crée un spy d'une biblio
	  Bibliothèque leSpy = Mockito.spy(biblio);
	  //on setup le psy pour que l'isbn 1 retourne la notice 2 au lieu de la 1
	  when(leSpy.getNoticeByIsbn("1")).thenReturn(notice2);
	  leSpy.addNotice(notice1);
	  //on check que le spy est bien opérationnel
	  assertEquals(leSpy.getNoticeByIsbn("1"),notice2);
  }
}
  