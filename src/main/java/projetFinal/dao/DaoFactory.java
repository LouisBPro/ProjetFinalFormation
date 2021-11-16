package projetFinal.dao;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {
	private static final DaoFactory instance = new DaoFactory();

	@SuppressWarnings("rawtypes")
	private Map<Class, Object> mapHolder = new HashMap<Class, Object>();

	private DaoFactory() {
	}

	@SuppressWarnings("unchecked")
	public static <T> T getInstance(Class<T> classOf) {

		synchronized (instance) {

			if (!instance.mapHolder.containsKey(classOf)) {

				T obj=null;
				try {
					obj = classOf.getDeclaredConstructor().newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
				instance.mapHolder.put(classOf, obj);
			}

			return (T) instance.mapHolder.get(classOf);

		}

	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
