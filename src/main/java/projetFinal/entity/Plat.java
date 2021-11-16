package projetFinal.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "plat")
@SequenceGenerator(name = "seqPlat", sequenceName = "seq_plat", allocationSize = 1)
public class Plat {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPlat")
	@Column(name = "plat_id")
	private Long id;
	@Column(name = "plat_nom", nullable = false, length = 200)
	private String nom;
	@Column(name = "plat_prix")
	private int prix;
	@Column(name = "plat_dispo")
	private Boolean dispo;
	@Lob
	@Column(name = "plat_description")
	private String description;
	@Lob
	@Column(name = "plat_photo")
	private byte[] photo;
	@OneToMany(mappedBy = "id.plat")
	private Set<LigneCommande> lignesCommandes;

	public Plat() {

	}

	public Plat(String nom, String description) {
		this.nom = nom;
		this.description = description;
	}

	public Plat(String nom, String description, byte[] photo) {
		this.nom = nom;
		this.description = description;
		this.photo = photo;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Set<LigneCommande> getLignesCommandes() {
		return lignesCommandes;
	}

	public void setLignesCommandes(Set<LigneCommande> lignesCommandes) {
		this.lignesCommandes = lignesCommandes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plat other = (Plat) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
