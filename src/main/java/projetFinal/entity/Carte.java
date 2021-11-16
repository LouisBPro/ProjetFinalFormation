package projetFinal.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Carte {
	@Column(name = "carte_restaurant_id")
	private Restaurant restaurant;
	@Column(name = "carte_plats")
	private Set<Plat> plats;
	

}
