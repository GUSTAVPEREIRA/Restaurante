package estagio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import estagio.model.ItensVenda;
import estagio.model.Produto;
import estagio.model.Venda;
import estagio.persistence.JPAUtil;

public class ItensVendaDAO extends GenericDAO<ItensVenda> {

	public ItensVendaDAO() {
		super(ItensVenda.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getID(ItensVenda obj) {
		return obj.getId();
	}

	public List<ItensVenda> listar(Venda venda) {
		String jpql;

		List<ItensVenda> retorno = new ArrayList<ItensVenda>();
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		try {

			jpql = "SELECT m FROM ItensVenda m where m.venda.id = :pVenda";

			TypedQuery<ItensVenda> query = em.createQuery(jpql, ItensVenda.class);
			query.setParameter("pVenda", venda.getId());

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

	public ItensVenda listar(Venda venda, Produto produto) {
		String jpql;
		ItensVenda itensVenda = new ItensVenda();
		List<ItensVenda> retorno = new ArrayList<ItensVenda>();
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		try {

			jpql = "SELECT m FROM ItensVenda m where m.venda.id = :pVenda and m.produto.id = :pProduto";

			TypedQuery<ItensVenda> query = em.createQuery(jpql, ItensVenda.class);
			query.setParameter("pVenda", venda.getId());
			query.setParameter("pProduto", produto.getId());
			retorno = query.getResultList();
			itensVenda = retorno.get(0);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return itensVenda;
	}

}
