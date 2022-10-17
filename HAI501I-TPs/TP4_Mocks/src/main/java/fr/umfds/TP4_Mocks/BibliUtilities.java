package fr.umfds.TP4_Mocks;

import java.util.ArrayList;
import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BibliUtilities {
  public Clock clock;
  public InterfaceGlobalBibliographyAccess global;

  //Constructeur par défaut
  public BibliUtilities() {}

  public NoticeStatus ajoutNotice(String isbn) throws AjoutImpossibleException {
    NoticeBibliographique notice;
    //On vérifie si la notice existe
    try {
      notice = global.getNoticeFromIsbn(isbn);
    }
    //Si elle n'existe pas on renvoie une exception
    catch (IncorrectIsbnException exception) {
      throw new AjoutImpossibleException();
    }
    //Elle existe donc on l'ajoute
    Bibliothèque biblio = Bibliothèque.getInstance();
    return biblio.addNotice(notice);
  }

  public ArrayList<NoticeBibliographique> chercherNoticesConnexes(NoticeBibliographique reference) {
    //Notices à retourner
    ArrayList<NoticeBibliographique> noticesConnexes = new ArrayList<NoticeBibliographique>();
    //Liste des notices ayant le même auteur
    ArrayList<NoticeBibliographique> memeAuteur = global.noticesDuMemeAuteurQue(reference);
    for (int i=0; i<memeAuteur.size() && noticesConnexes.size()<6; i++) {
      //Ajouter celles qui ne sont pas celle passée en paramètre
      if (!memeAuteur.get(i).getTitre().equals(reference.getTitre())) {
        noticesConnexes.add(memeAuteur.get(i));
      }
    }
    return noticesConnexes;
  }

  public void prevoirInventaire() {
    LocalDate today = LocalDate.now(clock);
    Bibliothèque biblio = Bibliothèque.getInstance();
    LocalDate lastInv = biblio.getLastInventaire();
    //Si le dernier inventaire a plus d'un an, on en refait un
    long difference = ChronoUnit.MONTHS.between(lastInv, today);
    if (difference >= 12) {
      biblio.inventaire();
    }
  }
}