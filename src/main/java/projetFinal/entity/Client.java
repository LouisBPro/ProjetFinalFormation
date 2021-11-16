package projetFinal.entity;


import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
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
	private Set<Commande> commandes;
	@Version
	@Column(name = "client_version")
	private int version;

	public Client() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqClient")
	@Override
	public Long getId() {
		return super.getId();
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
