package estagio.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import estagio.model.ContasPagar;
import estagio.persistence.JPAUtil;

public class ContasPagarDAO extends GenericDAO<ContasPagar> {

	public ContasPagarDAO() {
		super(ContasPagar.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getID(ContasPagar obj) {
		return obj.getId();
	}
//
//	private void updateDataVencida() {
//		String jpql;
//
//		int retorno;
//		EntityManager em = JPAUtil.getEntityManager();
//		
//		em.getTransaction().begin();
//		try {
//
//			jpql = "UPDATE ContasPagar c set c.status=:pStatus where :pDataHoje > c.vencimento";
//			TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
//			retorno = query.getSingleResult();
//			em.getTransaction().commit();
//
//		} catch (Exception e) {
//			em.getTransaction().rollback();
//			System.out.println(e.getMessage());
//		} finally {
//			em.close();
//		}
//	}

	public List<ContasPagar> listaContasPagar(Date dataAbertura, Date DataVencimento) {
		String jpql;

		List<ContasPagar> retorno = new ArrayList<ContasPagar>();
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		try {

			jpql = "SELECT m FROM ContasPagar m ";
			
			if (dataAbertura != null || DataVencimento != null) {
				jpql = jpql + "WHERE ";
			}

			if (dataAbertura != null && DataVencimento != null) {

				jpql = jpql + "m.abertura >= :pAbertura AND m.vencimento <= :pVencimento";
			} else {
				if (dataAbertura != null) {
					jpql = jpql + "m.abertura = :pAbertura";
				}
				if (DataVencimento != null)
					jpql = jpql + "m.vencimento = :pVencimento";
			}

			TypedQuery<ContasPagar> query = em.createQuery(jpql, ContasPagar.class);
			if (dataAbertura != null)
				query.setParameter("pAbertura", dataAbertura);
			if (DataVencimento != null)
				query.setParameter("pVencimento", DataVencimento);

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
