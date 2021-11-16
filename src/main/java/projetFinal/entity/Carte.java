package projetFinal.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carte")
public class Carte {
	@Column(name = "carte_restaurant_id")
	private Restaurant restaurant;
	@Column(name = "carte_plats")
	private Set<Plat> plats;
	
	public Carte() {
		super();
	}
	
	public Carte(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}

	public Carte(Restaurant restaurant, Set<Plat> plats) {
		super();
		this.restaurant = restaurant;
		this.plats = plats;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public Set<Plat> getPlats() {
		return plats;
	}
	public void setPlats(Set<Plat> plats) {
		this.plats = plats;
	}
	@Override
	public int hashCode() {
		return Objects.hash(plats, restaurant);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carte other = (Carte) obj;
		return Objects.equals(plats, other.plats) && Objects.equals(restaurant, other.restaurant);
	}
	public void AddPlat(Plat plat) {
		this.plats.add(plat);
	}
	public void removePlat(Plat plat) {
		this.plats.remove(plat);
	}
}
