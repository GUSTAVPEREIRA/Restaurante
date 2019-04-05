/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import estagio.model.Produto;
import estagio.view.util.JPAUtil;

/**
 *
 * @author Pereira
 */
public class ProdutoDAO extends GenericDAO<Produto>{

	@PersistenceContext
	EntityManager em;

	public ProdutoDAO() {
		super(Produto.class);
	}

	@Override
	public Long getID(Produto obj) {
		return obj.getId();
	}

	@Transactional
	public void inserir(Produto produto) {
		em = new JPAUtil().getEntityManager();
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			em.persist(produto);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void alterar(Produto produto) {
		em = new JPAUtil().getEntityManager();
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			Produto aux = em.find(Produto.class, produto.getId());
			aux.setNome(produto.getNome());
			aux.setFornecedor(produto.getFornecedor());
			aux.setCategoria(produto.getCategoria());
			aux.setPreco(produto.getPreco());
			aux.setPreco_compra(produto.getPreco_compra());
			em.merge(aux);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public boolean Deletar(Produto produto) {
		boolean deletado = false;
		em = new JPAUtil().getEntityManager();
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			Produto aux = em.find(Produto.class, produto.getId());
			em.remove(aux);
			em.getTransaction().commit();
			deletado = true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();

		}
		return deletado;
	}

	public Produto busca(String busca) {
		Produto produto = new Produto();
		em = new JPAUtil().getEntityManager();
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			produto = em.find(Produto.class, busca);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return produto;
	}

	public Produto listar(Long busca) {
		em = new JPAUtil().getEntityManager();
		String jpql = "";
		Produto produto = null;
		List<Produto> retorno = new ArrayList<Produto>();
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {
			jpql = "select m from Produto m where m.id = :pId";
			TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
			query.setParameter("pId", busca);
			retorno = query.getResultList();
			produto = retorno.get(0);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return produto;
	}

	public List<Produto> listar(String busca) {
		em = new JPAUtil().getEntityManager();
		String jpql = "";
		List<Produto> retorno = new ArrayList<Produto>();
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {
			if (busca.compareTo("") == 0)
				jpql = "select m from Produto m order by m.nome";
			else
				jpql = "select m from Produto m where m.nome like :pBusca "
						+ "OR m.fornecedor.nome like :pBusca order by m.nome";

			TypedQuery<Produto> query = em.createQuery(jpql,Produto.class);
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

	public List<Produto> listarPorCategoria(String busca) {
		em = new JPAUtil().getEntityManager();
		String jpql = "";
		List<Produto> retorno = new ArrayList<Produto>();
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {
			if (busca.compareTo("") == 0)
				jpql = "select m from Produto m order by m.nome";
			else
				jpql = "select m from Produto m where m.categoria.nome = :pBusca";

			TypedQuery<Produto> query = em.createQuery(jpql,Produto.class);
			if (busca.compareTo("") != 0)
				query.setParameter("pBusca", busca);
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
