package estagio.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import estagio.model.ContasReceber;
import estagio.persistence.JPAUtil;

public class ContasReceberDAO extends GenericDAO<ContasReceber>  {
	public ContasReceberDAO() {
		super(ContasReceber.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getID(ContasReceber obj) {
		return obj.getId();
	}
	
	public List<ContasReceber> listaContasReceber(Date dataAbertura, Date DataVencimento) {
		String jpql;

		List<ContasReceber> retorno = new ArrayList<ContasReceber>();
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		try {

			jpql = "SELECT m FROM ContasReceber m ";
			
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

			TypedQuery<ContasReceber> query = em.createQuery(jpql, ContasReceber.class);
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
