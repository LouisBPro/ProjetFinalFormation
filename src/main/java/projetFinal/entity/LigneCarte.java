package projetFinal.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class LigneCarte {
	@EmbeddedId
	private LigneCartePk id;
	@Column(name = "ligne_carte_disponibilité")
	private Boolean disponibilite;
	@Column(name = "ligne_carte_prix")
	private Float prix;

	public LigneCartePk getId() {
		return id;
	}

	public void setId(LigneCartePk ligneCartePk) {
		this.id = ligneCartePk;
	}

	public Boolean getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(Boolean disponibilite) {
		this.disponibilite = disponibilite;
	}

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}

	public LigneCarte() {
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
		LigneCarte other = (LigneCarte) obj;
		return Objects.equals(id, other.id);
	}

	
}
