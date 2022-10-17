package fr.umfds.TP4_Mocks;

import java.util.ArrayList;

public class GlobalBibliographyAccess implements InterfaceGlobalBibliographyAccess{

	@Override
	public NoticeBibliographique getNoticeFromIsbn(String isbn) throws IncorrectIsbnException {
		throw new UnsupportedOperationException();
	}

	@Override
	public ArrayList<NoticeBibliographique>noticesDuMemeAuteurQue(NoticeBibliographique ref){
		throw new UnsupportedOperationException();
	}
	
	@Override
	public ArrayList<NoticeBibliographique> autresEditions(NoticeBibliographique ref){
		throw new UnsupportedOperationException();
	}
}
