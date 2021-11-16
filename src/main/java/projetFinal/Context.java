package projetFinal;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Context {
	private EntityManagerFactory emf = null;

	private Context() {
		this.emf = Persistence.createEntityManagerFactory("eshop");
	}

	private static Context singleton = null;

	public static Context getInstance() {
		if (singleton == null) {
			singleton = new Context();
		}
		return singleton;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	public static void destroy() {
		if (singleton != null) {
			singleton.getEntityManagerFactory().close();
			singleton = null;
		}
	}
}
