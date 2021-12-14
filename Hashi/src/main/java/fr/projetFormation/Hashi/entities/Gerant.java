package fr.projetFormation.Hashi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "gerant")
@SequenceGenerator(name = "seqGerant", sequenceName = "seq_gerant", initialValue = 200, allocationSize = 1)
public class Gerant extends Personne {
    @OneToMany(mappedBy="gerant")
    @JsonView(JsonViews.GerantAvecRestaurantsEtUser.class)
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
