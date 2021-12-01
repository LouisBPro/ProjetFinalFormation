package fr.projetFormation.Hashi.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cuisinier")
@SequenceGenerator(name = "seqCuisinier", sequenceName = "seq_cuisinier", initialValue = 300, allocationSize = 1)
public class Cuisinier extends Personne {
	@ManyToOne
	@JoinColumn(name = "cuisinier_restaurant_id", foreignKey = @ForeignKey(name = "cuisinier_restaurant_id_fk"))
    private Restaurant restaurant;
  

    public Cuisinier(String nom, String prenom) {
		super(nom, prenom);
	}


	public Cuisinier() {
    	super();
    }
 
    
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
