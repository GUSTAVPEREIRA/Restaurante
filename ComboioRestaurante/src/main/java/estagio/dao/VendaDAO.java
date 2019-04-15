package estagio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import estagio.model.Venda;
import estagio.persistence.JPAUtil;

public class VendaDAO extends GenericDAO<Venda>{

	@PersistenceContext
	EntityManager em;

	public VendaDAO() {
		super(Venda.class);
	}

	@Override
	public Long getID(Venda obj) {
		return obj.getId();
	}
	
	public List<Venda> listaVenda() {
		String jpql;
		List<Venda> retorno = new ArrayList<Venda>();
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		try {

			jpql = "select m from Venda m where m.status = 'ABERTO'";
			TypedQuery<Venda> query = em.createQuery(jpql, Venda.class);
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
	
	public Venda FindToComanda(int comanda) {
		String jpql;
		List<Venda> retorno = new ArrayList<Venda>();
		EntityManager em = JPAUtil.getEntityManager();
		Venda venda = new Venda();
		em.getTransaction().begin();
		try {

			jpql = "select m from Venda m where m.status = 'ABERTO' and m.comanda = :pComanda";
			TypedQuery<Venda> query = em.createQuery(jpql, Venda.class);
			query.setParameter("pComanda", comanda);
			retorno = query.getResultList();
			venda = retorno.get(0);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return venda;
	}
}
