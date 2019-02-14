/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.dao;

import estagio.model.Fornecedor;
import estagio.view.util.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 *
 * @author Pereira
 */
public class FornecedorDAO {
@PersistenceContext
    EntityManager em;
    
    public FornecedorDAO() 
    {
        em = new JPAUtil().getEntityManager();
    }
    
    @Transactional
    public void inserir(Fornecedor fornecedor) 
    {
        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {

            em.persist(fornecedor);
            em.getTransaction().commit();

        } 
        catch(Exception e)
        {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        finally
        {
            em.close();
        }
    }
    
    public void alterar(Fornecedor fornecedor) 
    {
        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {

            Fornecedor aux = em.find(Fornecedor.class,fornecedor.getId());
            aux.setNome(fornecedor.getNome());
            aux.setTelefone(fornecedor.getTelefone());
            aux.setCep(fornecedor.getCep());
            aux.setCnpj(fornecedor.getCnpj());
            aux.setIe(fornecedor.getIe());
            aux.setCidade(fornecedor.getCidade());
            em.merge(aux);
            em.getTransaction().commit();

        } 
        catch(Exception e)
        {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        finally
        {
            em.close();
        }
    }

    public boolean Deletar(Fornecedor fornecedor) {
        boolean deletado = false;
        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {

            Fornecedor aux = em.find(Fornecedor.class,fornecedor.getId());
            em.remove(aux);            
            deletado = true;
            em.getTransaction().commit();
        } 
        catch(Exception e)
        {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        finally
        {
            em.close();            
        }
        return deletado;
    }
    
    public Fornecedor busca(String busca) {
        Fornecedor fornecedor = new Fornecedor();

        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {

            fornecedor = em.find(Fornecedor.class,busca);
            em.getTransaction().commit();

        } 
        catch(Exception e)
        {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        finally
        {
            em.close();
        }
        return fornecedor;
    }
    
    public Fornecedor listar(Long busca) {
        String jpql;
        Fornecedor fornecedor=null;
        List<Fornecedor> retorno = new ArrayList<Fornecedor>();
        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {
            jpql = "select m from Fornecedor m where m.id = :pId";  
            Query query = em.createQuery(jpql);
            query.setParameter("pId", busca);  
            retorno = query.getResultList();
            em.getTransaction().commit();
            fornecedor = retorno.get(0);

        } 
        catch(Exception e)
        {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        finally
        {
            em.close();
        }
        return fornecedor;
    }
    
    public List<Fornecedor> listar(String busca) {
        String jpql;
        List<Fornecedor> retorno = new ArrayList<Fornecedor>();
        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {
            if(busca.compareTo("") == 0)
                jpql = "select m from Fornecedor m";
            else
                jpql = "select m from Fornecedor m where m.nome like :pBusca "
                        + "OR m.cidade.nome like :pBusca";  

            Query query = em.createQuery(jpql);
            if (busca.compareTo("")!=0) 
                query.setParameter("pBusca", "%"+busca+"%");  
            retorno = query.getResultList();
            em.getTransaction().commit();

        } 
        catch(Exception e)
        {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        finally
        {
            em.close();
        }
        return retorno;
    }
}
