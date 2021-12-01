package fr.projetFormation.Hashi.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class LigneCartePk implements Serializable{
	@ManyToOne
	@JoinColumn(name = "ligne_carte_plat_id", foreignKey = @ForeignKey(name = "ligne_carte_plat_id_fk"))
	private Plat plat;
	@ManyToOne
	@JoinColumn(name = "ligne_carte_restaurant_id", foreignKey = @ForeignKey(name = "ligne_carte_restaurant_id_fk"))
	private Restaurant restaurant;

	public LigneCartePk() {
	}

	public LigneCartePk(Plat plat, Restaurant restaurant) {
		super();
		this.plat = plat;
		this.restaurant = restaurant;
	}

	public Plat getPlat() {
		return plat;
	}

	public void setPlat(Plat plat) {
		this.plat = plat;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((plat == null) ? 0 : plat.hashCode());
		result = prime * result + ((restaurant == null) ? 0 : restaurant.hashCode());
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
		LigneCartePk other = (LigneCartePk) obj;
		if (plat == null) {
			if (other.plat != null)
				return false;
		} else if (!plat.equals(other.plat))
			return false;
		if (restaurant == null) {
			if (other.restaurant != null)
				return false;
		} else if (!restaurant.equals(other.restaurant))
			return false;
		return true;
	}

	

}
