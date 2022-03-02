package persistance;

import java.util.List;

public class Utilisateur implements mediatek2022.Utilisateur{
	private int id;
	private String name;
	private String password;
	private String isBiblioth�caire;
	private List<Document> documentsEmpruntes;
	
	
	public Utilisateur(int id, String name, String password, String isBiblioth�caire) { //, List<Document> documentsEmpruntes
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.isBiblioth�caire = isBiblioth�caire;
		this.documentsEmpruntes = documentsEmpruntes;
	}

	@Override
	public String name() {
		return this.name;
	}
	
	public String password() {
		return this.password;
	}

	@Override
	public boolean isBibliothecaire() {
		if(Integer. parseInt(isBiblioth�caire) == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Object[] data() {
		return null;
	}

}
