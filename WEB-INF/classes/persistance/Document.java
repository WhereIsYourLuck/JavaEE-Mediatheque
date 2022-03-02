package persistance;

import mediatek2022.Utilisateur;

public class Document implements mediatek2022.Document{
	private int id;
	private String auteur;
	private String nomDoc;
	private String typeDoc;
	private int empruntUser;
	
	public Document(int id, String auteur, String nomDoc, String typeDoc, int empruntUser) {
		super();
		this.id = id;
		this.auteur = auteur;
		this.nomDoc = nomDoc;
		this.typeDoc = typeDoc;
		this.empruntUser = empruntUser;
	}
	@Override
	public boolean disponible() {
		if(empruntUser == 0) {
			return true;
		} else
		return false;
	}

	@Override
	public void emprunt(Utilisateur u) throws Exception {
	}

	@Override
	public void retour() {
	}

}
