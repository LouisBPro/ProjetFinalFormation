package fr.projetFormation.Hashi.entities;

public class JsonViews {
	public static class Common {}

	public static class RestaurantAvecLignesCarte extends Common{}

	public static class RestaurantAvecEmployes extends Common{}

	public static class RestaurantAvecTout extends RestaurantAvecEmployes{}

	public static class ClientWithCommandes extends Common{}

	public static class UserWithPersonne extends Common{}

	public static class PersonneWithUser extends Common{}
}
