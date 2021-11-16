package projetFinal.entity;


public enum Statut {
	Validated("validée"), Waiting("en attente d'acceptation"), Delivered("livrée"),
	Preparation("en cours de préparation"), Done("réalisée"), Cancelled("annulée");

	private String type;

	private Statut(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
