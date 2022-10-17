package fr.umfds.TP4_Mocks;

import java.util.ArrayList;

public interface InterfaceGlobalBibliographyAccess {

	NoticeBibliographique getNoticeFromIsbn(String isbn) throws IncorrectIsbnException;

	ArrayList<NoticeBibliographique> noticesDuMemeAuteurQue(NoticeBibliographique reference);

	ArrayList<NoticeBibliographique> autresEditions(NoticeBibliographique reference);

}
