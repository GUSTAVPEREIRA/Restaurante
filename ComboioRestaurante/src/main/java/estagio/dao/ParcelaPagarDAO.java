package estagio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import estagio.model.ContasPagar;
import estagio.model.ParcelaPagar;
import estagio.persistence.JPAUtil;

public class ParcelaPagarDAO extends GenericDAO<ParcelaPagar>{

	public ParcelaPagarDAO() {
		super(ParcelaPagar.class);
	}

	@Override
	public Long getID(ParcelaPagar obj) {
		return obj.getId();
	}

	
	public List<ParcelaPagar> listaParcelaPagar(ContasPagar contasPagar) {
		String jpql;
		
		List<ParcelaPagar> retorno = new ArrayList<ParcelaPagar>();
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		try {

			jpql = "SELECT m FROM ParcelaPagar m where m.contasPagar =:pContasPagar ";
	
            
			TypedQuery<ParcelaPagar> query = em.createQuery(jpql, ParcelaPagar.class);
			if (contasPagar != null) {
				query.setParameter("pContasPagar", contasPagar);  
			}
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
