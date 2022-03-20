package persistance;


import java.util.List;

import mediatek2022.Utilisateur;

public class PersisUtilisateur implements Utilisateur{
	private int idUser;
	private String name;
	private String password;
	private String isBibliothecaire;
	private List<Utilisateur> documentsEmpruntes;
	
	public PersisUtilisateur(int id, String name, String password, String isBibliothecaire) {
		this.idUser = id;
		this.name = name;
		this.password = password;
		this.isBibliothecaire = isBibliothecaire;
	}

	public List<Utilisateur> getDocumentsEmpruntes() {
		return documentsEmpruntes;
	}

	public void setDocumentsEmpruntes(List<Utilisateur> documentsEmpruntes) {
		this.documentsEmpruntes = documentsEmpruntes;
	}

	@Override
	public String name() {
		return this.name;
	}

	@Override
	public boolean isBibliothecaire() {
		if(Integer.parseInt(isBibliothecaire) == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Object[] data() {
		//Passage de tous les autres attributs utiles ici
		//MDP, tableau de livre emprunté
		//Très utile car les instances sont volatiles : On recrée le même utilisateur à chaque modification

		Object[] data = new Object[4];
		data[0] = this.name();
		data[1] = this.password;
		data[2] = this.isBibliothecaire;
		data[3] = this.documentsEmpruntes;
		
		return data;
	}

}
