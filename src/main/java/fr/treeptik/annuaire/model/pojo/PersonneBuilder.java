package fr.treeptik.annuaire.model.pojo;

import java.util.Date;
import java.util.HashSet;
									//pattern builder
public class PersonneBuilder {     //crée des intances de personne avec les attributs qui vont mais sans les collections
	
	private Personne personne;
	
	public static PersonneBuilder createInstance() {
		PersonneBuilder builder = new PersonneBuilder();
		builder.personne = new Personne();
		return builder;
	}
	
	public PersonneBuilder withNom(String nom){
		this.personne.setNom(nom);
		return this;
	}
	
	public PersonneBuilder withPrenom(String prenom){
		this.personne.setPrenom(prenom);
		return this;
	}
	
	public PersonneBuilder withDateNaissance(Date dateNaissance){
		this.personne.setDateNaissance(dateNaissance);
		return this;
	}
	
	public PersonneBuilder withNum(Numero numero){
		if (personne.getNumerosTel() == null) {
			personne.setNumerosTel(new HashSet<>());
		}
		this.personne.getNumerosTel().add(numero);
		return this;
	}

	public Personne build() {
		return this.personne;
	}
	
}
