package estagio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import estagio.model.Caixa;
import estagio.persistence.JPAUtil;

public class CaixaDAO extends GenericDAO<Caixa> {

	public CaixaDAO() {
		super(Caixa.class);
	}

	@Override
	public Long getID(Caixa obj) {
		return obj.getId();
	}

	public Caixa caixa_aberto(String busca) {
		Caixa usuario = null;
		String jpql;
		List<Caixa> retorno = new ArrayList<Caixa>();
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		try {

			jpql = "select m from Caixa m where m.status = :pStatus";
			TypedQuery<Caixa> query = em.createQuery(jpql, Caixa.class);
			query.setParameter("pStatus", busca);
			retorno = query.getResultList();
			usuario = retorno.get(0);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return usuario;
	}

	public List<Caixa> listaCaixas() {
		String jpql;
		List<Caixa> retorno = new ArrayList<Caixa>();
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		try {

			jpql = "select m from Caixa m where m.status = 'ABERTO'";
			TypedQuery<Caixa> query = em.createQuery(jpql, Caixa.class);
			retorno = query.getResultList();
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return retorno;
	}
}
