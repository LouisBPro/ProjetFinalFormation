package fr.projetFormation.Hashi.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ligne_commande")
public class LigneCommande {
	@EmbeddedId
	private LigneCommandePK id;
	@Column(name = "ligne_commande_quantite", nullable = false)
	private int quantite;

	public LigneCommande() {

	}

	public LigneCommande(LigneCommandePK id, int quantite) {
		this.id = id;
		this.quantite = quantite;
	}

	public LigneCommandePK getId() {
		return id;
	}

	public void setId(LigneCommandePK id) {
		this.id = id;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
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
		LigneCommande other = (LigneCommande) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
