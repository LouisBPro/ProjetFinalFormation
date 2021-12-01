package fr.projetFormation.Hashi.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "commande")
@SequenceGenerator(name = "seqCommande", sequenceName = "seq_commande", allocationSize = 1)
public class Commande {
	@Id
	@Column(name = "commande_id")
	@GeneratedValue(generator = "seqCommande", strategy = GenerationType.SEQUENCE)
	@JsonView(JsonViews.Common.class)
	private Long id;
	@NotNull
	@ManyToOne
	@JoinColumn(name="commande_client_id", foreignKey = @ForeignKey(name="commande_client_id_fk"))
	@JsonView(JsonViews.Common.class)
	private Client client;
	@Column(name = "commande_date", nullable = false)
	@JsonView(JsonViews.Common.class)
	private LocalDate date = LocalDate.now();
	@OneToMany(mappedBy="id.commande")
	@JsonView(JsonViews.Common.class)
	private Set<LigneCommande> lignesCommande = new HashSet<LigneCommande>();
	@OneToOne
	@JoinColumn(name="commande_restaurant_id", foreignKey = @ForeignKey(name="commande_restaurant_id_fk"))
	@JsonView(JsonViews.Common.class)
	private Restaurant restaurant;
	@Enumerated(EnumType.STRING)
	@Column(name = "commande_statut")
	@JsonView(JsonViews.Common.class)
	private Statut statut = Statut.Validated;
	@Column(name = "commande_prix_total")
	@JsonView(JsonViews.Common.class)
	private Float prixTotal;
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
		return lignesCommande;
	}

	public void setLigneCommandes(Set<LigneCommande> ligneCommandes) {
		this.lignesCommande = ligneCommandes;
	}

	public Statut getStatut() {
		return statut;
	}

	public Set<LigneCommande> getLignesCommande() {
		return lignesCommande;
	}

	public void setLignesCommande(Set<LigneCommande> lignesCommande) {
		this.lignesCommande = lignesCommande;
	}

	public Float getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Float prixTotal) {
		this.prixTotal = prixTotal;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
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

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setResto(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public void addLigneCommande(LigneCommande ligneCommande) {
		this.lignesCommande.add(ligneCommande);
	}

	public void removeLigneCommande(LigneCommande ligneCommande) {
		this.lignesCommande.remove(ligneCommande);
	}
}
