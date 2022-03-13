package persistance;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

public class PersisDocument implements Document{
	private String auteur;
	private String nomDoc;
	private String typeDoc;
	private int empruntUser;
	
	public PersisDocument(String auteur, String nomDoc, String typeDoc, int empruntUser) {
		super();
		this.auteur = auteur;
		this.nomDoc = nomDoc;
		this.typeDoc = typeDoc;
		this.empruntUser = empruntUser;
	}

	@Override
	public boolean disponible() {
		if(empruntUser != 0) 
			return false;
		else
			return true;
	}

	@Override
	public void emprunt(Utilisateur u) throws Exception {
		
	}

	@Override
	public void retour() {
		
	}

}
