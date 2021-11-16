package projetFinal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cuisinier")
@SequenceGenerator(name = "seqCuisinier", sequenceName = "seq_cuisinier", initialValue = 300, allocationSize = 1)
public class Cuisinier extends Personne {
    // @OneToOne(mappedBy = "client")
    // @Column
    private Restaurant restaurant;
    @Version
    @Column(name = "gerant_version")
    private int version;

    public Cuisinier() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGerant")
    @Override
    public Long getId() {
        return super.getId();
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
