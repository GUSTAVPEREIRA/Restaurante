package estagio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import estagio.model.Empresa;
import estagio.view.util.JPAUtil;

public class EmpresaDAO {

	@PersistenceContext
	EntityManager em;

	public EmpresaDAO() {
		em = new JPAUtil().getEntityManager();
	}

	@Transactional
	public void inserir(Empresa empresa) {
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			em.persist(empresa);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void alterar(Empresa empresa) {
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {

			Empresa aux = em.find(Empresa.class, empresa.getId());
			aux.setNome(empresa.getNome());
			aux.setCnpj(empresa.getCnpj());
			aux.setIe(empresa.getIe());
			aux.setCidade(empresa.getCidade());
			aux.setCaminho_imgem(empresa.getCaminho_imgem());
			aux.setCep(empresa.getCep());
			aux.setTelefone(empresa.getTelefone());

			em.merge(aux);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	public Empresa listar() {
		String jpql = "";
		Empresa empresa = null;
		List<Empresa> retorno = new ArrayList<Empresa>();
		if (!em.isOpen()) {
			em = new JPAUtil().getEntityManager();
		} else
			em.getTransaction().begin();
		try {
			jpql = "select m from Empresa m";
			TypedQuery<Empresa> query = em.createQuery(jpql, Empresa.class);
			retorno = query.getResultList();
			empresa = retorno.get(0);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return empresa;
	}
}
