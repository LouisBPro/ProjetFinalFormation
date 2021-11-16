package projetFinal.dao;

public class DaoPersonneFactory {
	private static DaoPersonne daoPersonne = null;

	public static DaoPersonne getInstance() {
		if (daoPersonne == null) {
			daoPersonne = new DaoPersonneJpaImpl();
		}
		return daoPersonne;
	}
}
