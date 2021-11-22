package projetFinal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "seqPersonne", sequenceName = "seq_personne", initialValue = 200, allocationSize = 1)
public abstract class Personne {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqPersonne")
	private Long id;
	@Column(name = "personne_prenom", length = 200)
	@NotBlank
	@NotEmpty
	private String prenom;
	@Column(name = "personne_nom", length = 200)
	@NotBlank
	@NotEmpty
	private String nom;
	@Column(name = "personne_email", length = 200)
	private String email;
	@Column(name = "personne_login", length = 30)
	private String login;
	@Column(name = "personne_password", length = 30)
	private String password;
	@Version
	@Column(name = "personne_version")
	private int version;

	public Personne() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void Connection() {
		// TODO
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
		Personne other = (Personne) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
