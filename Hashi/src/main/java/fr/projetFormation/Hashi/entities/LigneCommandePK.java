package fr.projetFormation.Hashi.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Embeddable
public class LigneCommandePK implements Serializable {
	@ManyToOne
	@JoinColumn(name = "ligne_commande_commande_id", foreignKey = @ForeignKey(name = "ligne_commande_commande_id_fk"))
	private Commande commande;
	@ManyToOne
	@JoinColumn(name = "ligne_commande_plat_id", foreignKey = @ForeignKey(name = "ligne_commande_plat_id_fk"))
	@JsonView(JsonViews.Common.class)
	private Plat plat;

	public LigneCommandePK() {

	}

	public LigneCommandePK(Commande commande, Plat plat) {
		this.commande = commande;
		this.plat = plat;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Plat getProduit() {
		return plat;
	}

	public void setProduit(Plat plat) {
		this.plat = plat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commande == null) ? 0 : commande.hashCode());
		result = prime * result + ((plat == null) ? 0 : plat.hashCode());
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
		LigneCommandePK other = (LigneCommandePK) obj;
		if (commande == null) {
			if (other.commande != null)
				return false;
		} else if (!commande.equals(other.commande))
			return false;
		if (plat == null) {
			if (other.plat != null)
				return false;
		} else if (!plat.equals(other.plat))
			return false;
		return true;
	}

}
