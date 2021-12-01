package fr.projetFormation.Hashi.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "ligne_carte")
public class LigneCarte {
	@EmbeddedId
	@JsonView({JsonViews.RestaurantAvecLignesCarte.class, JsonViews.RestaurantAvecTout.class})
	private LigneCartePk id;
	@Column(name = "ligne_carte_disponibilite")
	@JsonView({JsonViews.RestaurantAvecLignesCarte.class, JsonViews.RestaurantAvecTout.class})
	private Boolean disponibilite;
	

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

	public LigneCarte() {
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
		LigneCarte other = (LigneCarte) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
