package estagio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import estagio.model.TipoVenda;
import estagio.view.util.JPAUtil;

public class TipoVendaDAO {
	@PersistenceContext
	EntityManager em;

	public TipoVendaDAO() {
		em = new JPAUtil().getEntityManager();
	}

	@Transactional
	public void inserir(TipoVenda tipoVenda) {
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			em.persist(tipoVenda);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void alterar(TipoVenda tipoVenda) {
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			TipoVenda aux = em.find(TipoVenda.class, tipoVenda.getId());
			aux.setNome(tipoVenda.getNome());
			em.merge(aux);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("finally")
	public boolean Deletar(TipoVenda tipoVenda) {
		boolean deletado = false;

		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {
			TipoVenda aux = em.find(TipoVenda.class, tipoVenda.getId());
			em.remove(aux);
			deletado = true;
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
			return deletado;
		}
	}

	public TipoVenda busca(String busca) {
		TipoVenda tipoVenda = new TipoVenda();

		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			tipoVenda = em.find(TipoVenda.class, busca);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return tipoVenda;
	}

	@SuppressWarnings("unchecked")
	public TipoVenda listar(int busca) {
		String jpql = "";
		TipoVenda tipoVenda = null;
		List<TipoVenda> retorno = new ArrayList<TipoVenda>();
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {
			jpql = "select m from TipoVenda m where m.id = :pId";
			Query query = em.createQuery(jpql);
			query.setParameter("pId", busca);
			retorno = query.getResultList();
			tipoVenda = retorno.get(0);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return tipoVenda;
	}

	public List<TipoVenda> listar(String busca) {
		String jpql = "";
		List<TipoVenda> retorno = new ArrayList<TipoVenda>();
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			if (busca.compareTo("") == 0)
				jpql = "select m from TipoVenda m";
			else
				jpql = "select m from TipoVenda m where m.nome like :pBusca";

			Query query = em.createQuery(jpql);
			if (busca.compareTo("") != 0)
				query.setParameter("pBusca", "%" + busca + "%");
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
