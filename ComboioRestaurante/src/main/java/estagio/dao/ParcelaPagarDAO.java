package estagio.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import estagio.model.ContasPagar;
import estagio.model.ParcelaPagar;
import estagio.persistence.JPAUtil;

public class ParcelaPagarDAO extends GenericDAO<ParcelaPagar> {

	public ParcelaPagarDAO() {
		super(ParcelaPagar.class);
	}

	@Override
	public Long getID(ParcelaPagar obj) {
		return obj.getId();
	}

	public List<ParcelaPagar> listaParcelaPagar(ContasPagar contasPagar, Date dataAbertura, Date DataVencimento,
			String status) {
		String jpql;

		List<ParcelaPagar> retorno = new ArrayList<ParcelaPagar>();
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		try {

			jpql = "SELECT m FROM ParcelaPagar m where m.contasPagar =:pContasPagar ";
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
			TypedQuery<ParcelaPagar> query = em.createQuery(jpql, ParcelaPagar.class);
			if (contasPagar != null) {
				query.setParameter("pContasPagar", contasPagar);
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

	public boolean deletaParcelas(ContasPagar contasPagar) {
		String jpql;

		List<ParcelaPagar> retorno = new ArrayList<ParcelaPagar>();
		EntityManager em = JPAUtil.getEntityManager();
		Boolean delete = true;
		em.getTransaction().begin();
		try {

			jpql = "SELECT m FROM ParcelaPagar m where m.contasPagar =:pContasPagar ";

			TypedQuery<ParcelaPagar> query = em.createQuery(jpql, ParcelaPagar.class);
			if (contasPagar != null) {
				query.setParameter("pContasPagar", contasPagar);
			}
			retorno = query.getResultList();
			for (ParcelaPagar parcelaPagar : retorno) {
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
