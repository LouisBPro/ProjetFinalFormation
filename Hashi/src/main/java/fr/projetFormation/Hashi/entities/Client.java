package fr.projetFormation.Hashi.entities;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "client")
@SequenceGenerator(name = "seqClient", sequenceName = "seq_client", initialValue = 100, allocationSize = 1)
public class Client extends Personne {
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "numero", column = @Column(name = "personne_numero")),
			@AttributeOverride(name = "rue", column = @Column(name = "personne_rue")),
			@AttributeOverride(name = "codePostal", column = @Column(name = "personne_code_postal", length = 20)),
			@AttributeOverride(name = "ville", column = @Column(name = "personne_ville")) })
	private Adresse adresse;
	@OneToMany(mappedBy="client")
	private Set<Commande> commandes = new HashSet<Commande>();
	

	public Client() {

	}

	

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Set<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(Set<Commande> commandes) {
		this.commandes = commandes;
	}

	public void addCommande(Commande commande){
		commandes.add(commande);
	}

	public void removeCommande(Commande commande){
		commandes.remove(commande);
	}
}
