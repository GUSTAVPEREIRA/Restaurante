/**
 * 
 */
package estagio.dao;

import javax.persistence.EntityManager;

import estagio.persistence.JPAUtil;



/**
 * @author titan
 *
 */
public abstract class GenericDAO<Template> implements GenericDAOInterface<Template> {

	private final Class<Template> templateClass;

	public GenericDAO(Class<Template> templateClass) {
		this.templateClass = templateClass;
	}

	@Override
	public Template persist(Template obj) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		} finally {
			em.close();
		}

		return obj;
	}

	@Override
	public Template merge(Template obj) {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(obj);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		} finally {
			em.close();
		}

		return obj;
	}

	@Override
	public Template delete(Template obj) {

		EntityManager em = JPAUtil.getEntityManager();
		Template objr = null;
		try {
			em.getTransaction().begin();
			objr = em.merge(obj);
			em.remove(objr);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		} finally {
			em.close();
		}

		return objr;
	}

	@Override
	public Template findById(Long id) {

		EntityManager em = JPAUtil.getEntityManager();
		Template obj = null;
		try {
			em.getTransaction().begin();
			obj = em.find(templateClass, id);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		} finally {
			em.close();

		}

		return obj;
	}

	public Template save(Template obj) {
		
		if(this.getID(obj) == null) {
			this.persist(obj);
		}
		else {
			this.merge(obj);
		}
		
		return obj;
	}
	
	public abstract Long getID(Template obj);

}
