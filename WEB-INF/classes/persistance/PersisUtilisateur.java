package persistance;

import java.util.List;

import mediatek2022.Utilisateur;

public class PersisUtilisateur implements Utilisateur{
	private String name;
	private String password;
	private String isBibliothecaire;
	private List<Utilisateur> documentsEmpruntes;
	
	public PersisUtilisateur(String name, String password, String isBibliothecaire) {
		this.name = name;
		this.password = password;
		this.isBibliothecaire = isBibliothecaire;
	}

	@Override
	public String name() {
		return this.name();
	}

	@Override
	public boolean isBibliothecaire() {
		if(Integer.parseInt(isBibliothecaire) == 0) {
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
