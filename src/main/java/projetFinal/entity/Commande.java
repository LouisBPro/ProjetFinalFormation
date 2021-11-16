package projetFinal.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "commande")
@NamedQueries({
		@NamedQuery(name = "Commande.findByKeyWithLignesCommandes", query = "select distinct c from Commande c left join fetch c.lignesCommandes where c.numero=:numero"),
		@NamedQuery(name = "Commande.findAllWithLignesCommandes", query = "select distinct c from Commande c left join fetch c.lignesCommandes ") })
@SequenceGenerator(name = "seqCommande", sequenceName = "seq_commande", allocationSize = 1)
public class Commande {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCommande")
	@Column(name = "commande_numero")
	private Long numero;
	@Column(name = "commande_date")
	private LocalDate date = LocalDate.now();
	@ManyToOne
	@JoinColumn(name = "commande_client_id", foreignKey = @ForeignKey(name = "commande_client_id_fk"))
	private Client client;
	@OneToMany(mappedBy = "id.commande")
	private Set<LigneCommande> lignesCommandes = new HashSet<LigneCommande>();

	public Commande() {

	}

	public Commande(Client client) {
		this.client = client;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<LigneCommande> getLignesCommandes() {
		return lignesCommandes;
	}

	public void setLignesCommandes(Set<LigneCommande> lignesCommandes) {
		this.lignesCommandes = lignesCommandes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Commande other = (Commande) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	public void addProduit(Produit produit, int quantite) {
		lignesCommandes.add(new LigneCommande(new LigneCommandePK(this, produit), quantite));
	}

//	public void removeProduit(Produit produit) {
//		
//	}

}
