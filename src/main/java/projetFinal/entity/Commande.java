package projetFinal.entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "commande")
@SequenceGenerator(name = "seqCommande", sequenceName = "seq_commande", allocationSize = 1)
public class Commande {
	@Id
	@Column(name = "commande_id")
	@GeneratedValue(generator = "seqCommande", strategy = GenerationType.SEQUENCE)
	private Long id;
	@NotNull
	@Column(name = "commande_client_id", nullable = false)
	private Client client;
	// @Column(name = "commande_plat_id" ,nullable = false)
	// private Set<Plat> plats;
	@Column(name = "commande_date", nullable = false)
	private LocalDate date = LocalDate.now();
	@Column(name = "commande_ligne", nullable = false)
	private Set<LigneCommande> ligneCommandes;
	// @Column(name = "commande_restaurant_id", nullable = false)
	// private Restaurant resto;
	@Enumerated(EnumType.STRING)
	@Column(name = "commande_statut")
	private Statut statut = Statut.Validated;
	@Version
	private int version;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Set<LigneCommande> getLigneCommandes() {
		return ligneCommandes;
	}

	public void setLigneCommandes(Set<LigneCommande> ligneCommandes) {
		this.ligneCommandes = ligneCommandes;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Commande() {
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commande other = (Commande) obj;
		return Objects.equals(id, other.id);
	}

}
