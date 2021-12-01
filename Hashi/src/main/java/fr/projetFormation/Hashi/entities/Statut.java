package fr.projetFormation.Hashi.entities;


public enum Statut {
	Validated("validee"), Waiting("en attente d'acceptation"), Delivered("livree"),
	Preparation("en cours de preparation"), Done("realisee"), Cancelled("annulee");

	private String type;

	private Statut(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
