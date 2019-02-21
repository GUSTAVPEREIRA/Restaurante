package estagio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import estagio.model.Cliente;
import estagio.model.Estado;
import estagio.view.util.JPAUtil;

public class ClienteDAO {
	@PersistenceContext
	EntityManager em;

	public ClienteDAO() {
		em = new JPAUtil().getEntityManager();
	}

	@Transactional
	public void inserir(Cliente cliente) {
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			em.persist(cliente);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void alterar(Cliente cliente) {
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			Cliente aux = em.find(Cliente.class, cliente.getId());
			aux.setNome(cliente.getNome());
			aux.setCidade(cliente.getCidade());
			aux.setTelefone(cliente.getTelefone());
			aux.setCep(cliente.getCep());

			em.merge(aux);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public boolean Deletar(Cliente cidade) {
		boolean deletado = false;

		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			Cliente aux = em.find(Cliente.class, cidade.getId());
			em.remove(aux);
			deletado = true;
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return deletado;
	}

	public Cliente busca(String busca) {
		Cliente cidade = new Cliente();

		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			cidade = em.find(Cliente.class, busca);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return cidade;
	}

	public Cliente listar(int busca) {
		String jpql = "";
		Cliente cidade = null;
		List<Cliente> retorno = new ArrayList<Cliente>();
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {
			jpql = "select m from Cliente m where m.id = :pId";
			TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
			query.setParameter("pId", busca);
			retorno = query.getResultList();
			cidade = retorno.get(0);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return cidade;
	}

	public List<Cliente> listClientesPEstado(Estado busca) {
		String jpql = "";
		List<Cliente> retorno = new ArrayList<Cliente>();
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {
			jpql = "select r from Cliente r where r.nome.id=:pNome order by r.nome";
			TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
			if (busca != null)
				query.setParameter("pEstado", busca.getId());
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

	public List<Cliente> listar(String busca) {
		String jpql = "";
		List<Cliente> retorno = new ArrayList<Cliente>();
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			if (busca.compareTo("") == 0)
				jpql = "select m from Cliente m";
			else
				jpql = "select m from Cliente m where m.nome like :pBusca " + "OR m.estado.nome like :pBusca";

			TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
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
