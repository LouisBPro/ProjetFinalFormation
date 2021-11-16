package projetFinal.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "restaurant")
@SequenceGenerator(name = "seqRestaurant", sequenceName = "seq_restaurant", allocationSize = 1)
public class Restaurant {
	@Id
	@Column(name = "restaurant_id", nullable = false)
	@GeneratedValue(generator = "seqRestaurant", strategy = GenerationType.SEQUENCE)
	private Long id;
	@NotBlank
	@NotNull
	@Column(name = "restaurant_name")
	private String nom;
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "numero", column = @Column(name = "personne_numero")),
			@AttributeOverride(name = "rue", column = @Column(name = "personne_rue")),
			@AttributeOverride(name = "codePostal", column = @Column(name = "personne_code_postal", length = 20)),
			@AttributeOverride(name = "ville", column = @Column(name = "personne_ville")) })
	private Adresse adresse;
	@OneToOne
	@JoinColumn(name="restaurant_carte_id",foreignKey = @ForeignKey(name="restaurant_carte_id_fk"))
	private Carte carte;
	@ManyToOne
	@JoinColumn(name = "restaurant_gerant_id", foreignKey = @ForeignKey(name = "restaurant_gerant_id_fk"))
	private Gerant gerant;
	@ManyToOne
	@JoinColumn(name = "restaurant_cuisinier_id", foreignKey = @ForeignKey(name = "restaurant_cuisinier_id_fk"))
	private Set<Cuisinier> cuisiniers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}

	public Gerant getGerant() {
		return gerant;
	}

	public void setGerant(Gerant gerant) {
		this.gerant = gerant;
	}

	public Set<Cuisinier> getCuisiniers() {
		return cuisiniers;
	}

	public void setCuisiniers(Set<Cuisinier> cuisiniers) {
		this.cuisiniers = cuisiniers;
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
		Restaurant other = (Restaurant) obj;
		return Objects.equals(id, other.id);
	}

	public Restaurant() {
	}

	public void rejetCommande(Commande commande) {
		//TODO
	}
	
	public void addCuisinier(Cuisinier cuisinier) {
		cuisiniers.add(cuisinier);
	}
	public void removeCuisinier(Cuisinier cuisinier) {
		cuisiniers.remove(cuisinier);
	}
	
}
