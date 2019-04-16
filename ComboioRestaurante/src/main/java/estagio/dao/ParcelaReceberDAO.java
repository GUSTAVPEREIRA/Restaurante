package estagio.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import estagio.model.ContasReceber;
import estagio.model.ContasReceber;
import estagio.model.ParcelaReceber;
import estagio.model.ParcelaReceber;
import estagio.persistence.JPAUtil;

public class ParcelaReceberDAO extends GenericDAO<ParcelaReceber> {
	public ParcelaReceberDAO() {
		super(ParcelaReceber.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getID(ParcelaReceber obj) {
		return obj.getId();
	}
	
	public List<ParcelaReceber> listaParcelaReceber(ContasReceber contasReceber, Date dataAbertura, Date DataVencimento,
			String status) {
		String jpql;

		List<ParcelaReceber> retorno = new ArrayList<ParcelaReceber>();
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		try {

			jpql = "SELECT m FROM ParcelaReceber m where m.contasReceber =:pContasReceber ";
			if (status != null && status.equals("") != true && status.equals("AMBOS") != true) {
				jpql = jpql + "AND m.status = :pStatus ";
			}
			if (dataAbertura != null && DataVencimento != null) {

				jpql = jpql + "AND m.abertura >= :pAbertura AND m.vencimento <= :pVencimento ";
			} else {
				if (dataAbertura != null) {
					jpql = jpql + "AND m.abertura = :pAbertura ";
				}
				if (DataVencimento != null)
					jpql = jpql + "AND m.vencimento = :pVencimento ";
			}
			TypedQuery<ParcelaReceber> query = em.createQuery(jpql, ParcelaReceber.class);
			if (contasReceber != null) {
				query.setParameter("pContasReceber", contasReceber);
			}
			if (dataAbertura != null)
				query.setParameter("pAbertura", dataAbertura);
			if (DataVencimento != null)
				query.setParameter("pVencimento", DataVencimento);
			if (status != null && status.equals("") != true && status.equals("AMBOS") != true)
				query.setParameter("pStatus", status);
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
	
	public boolean deletaParcelas(ContasReceber contasReceber) {
		String jpql;

		List<ParcelaReceber> retorno = new ArrayList<ParcelaReceber>();
		EntityManager em = JPAUtil.getEntityManager();
		Boolean delete = true;
		em.getTransaction().begin();
		try {

			jpql = "SELECT m FROM ParcelaReceber m where m.contasReceber =:pContasReceber ";

			TypedQuery<ParcelaReceber> query = em.createQuery(jpql, ParcelaReceber.class);
			if (contasReceber != null) {
				query.setParameter("pContasReceber", contasReceber);
			}
			retorno = query.getResultList();
			for (ParcelaReceber parcelaPagar : retorno) {
				em.remove(parcelaPagar);
			}
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			delete = false;
		} finally {
			em.close();
		}
		return delete;
	}
}
