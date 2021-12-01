package fr.projetFormation.Hashi.entity;


public enum Statut {
	Validated("valid�e"), Waiting("en attente d'acceptation"), Delivered("livr�e"),
	Preparation("en cours de pr�paration"), Done("r�alis�e"), Cancelled("annul�e");

	private String type;

	private Statut(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
