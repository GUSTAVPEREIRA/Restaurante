/**
 * 
 */
package estagio.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Gabriel L. P. Abreu
 */
public class JPAUtil {

	private static final EntityManagerFactory entityManagerFactory = Persistence
//			.createEntityManagerFactory("DWORPostgreSQLPU");
			//.createEntityManagerFactory("DWORHyperSQLDBPU");
			.createEntityManagerFactory("estagio");

	public static EntityManager getEntityManager() {

		return entityManagerFactory.createEntityManager();
	}

	public static void StopEntityManagerFactory() throws Throwable {
		entityManagerFactory.close();
	}
}
