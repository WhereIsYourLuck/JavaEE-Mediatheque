package persistance;

import java.util.List;

public class Utilisateur implements mediatek2022.Utilisateur{
	private int id;
	private String name;
	private String isBiblioth�caire;
	private List<Document> documentsEmpruntes;
	
	
	public Utilisateur(int id, String name, String isBiblioth�caire, List<Document> documentsEmpruntes) {
		super();
		this.id = id;
		this.name = name;
		this.isBiblioth�caire = isBiblioth�caire;
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
