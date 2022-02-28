package persistance;

import java.util.List;

public class Utilisateur implements mediatek2022.Utilisateur{
	private int id;
	private String name;
	private String isBibliothécaire;
	private List<Document> documentsEmpruntes;
	
	
	public Utilisateur(int id, String name, String isBibliothécaire, List<Document> documentsEmpruntes) {
		super();
		this.id = id;
		this.name = name;
		this.isBibliothécaire = isBibliothécaire;
		this.documentsEmpruntes = documentsEmpruntes;
	}

	@Override
	public String name() {
		return null;
	}

	@Override
	public boolean isBibliothecaire() {
		return false;
	}

	@Override
	public Object[] data() {
		return null;
	}

}
