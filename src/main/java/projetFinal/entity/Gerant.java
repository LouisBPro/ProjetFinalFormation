package projetFinal.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "gerant")
@SequenceGenerator(name = "seqGerant", sequenceName = "seq_gerant", initialValue = 200, allocationSize = 1)
public class Gerant extends Personne {
    @OneToMany(mappedBy="gerant")
    private Set<Restaurant> restaurants = new HashSet<Restaurant>();


    public Gerant() {

    }


    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void addRestaurant(Restaurant restaurant){
        restaurants.add(restaurant);
    }

    public void removeRestaurants(Restaurant restaurant){
        restaurants.remove(restaurant);
    }

    public void creationCptRestau(){
        // TODO
    }
}
