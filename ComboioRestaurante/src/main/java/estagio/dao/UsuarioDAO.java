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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import estagio.model.Usuario;
import estagio.view.util.JPAUtil;

/**
 *
 * @author Pereira
 */
public class UsuarioDAO {
    
    @PersistenceContext
    EntityManager em;

    public UsuarioDAO() 
    {
        em = new JPAUtil().getEntityManager();
    }                

    public void inserir(Usuario usuario)
    {         
        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {
            em.persist(usuario);
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
    
    public void alterar(Usuario usuario) 
    {
        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {
            Usuario aux = em.find(Usuario.class,usuario.getId());
            aux.setNome(usuario.getNome());
            aux.setLogin(usuario.getLogin());
            aux.setSenha(usuario.getSenha());
            aux.setTipo(usuario.getTipo());
            aux.setAtivo(usuario.getAtivo());
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

    public boolean Deletar(Usuario usuario) {
        boolean deletado = false;

        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {
            Usuario aux = em.find(Usuario.class,usuario.getId());
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
    public void verif_admin(int codigo)
    {
     
    }
    
    public void qt_Admin() 
    {
   
            
          //  String sql = "SELECT COUNT(usu_tipo) as qt FROM usuario WHERE usu_tipo = 'ADMIN'";
 
    }
    
    @SuppressWarnings("unchecked")
	public Usuario login_dup(String busca) {
        String jpql;
        Usuario usuario = null;
        List<Usuario> retorno = new ArrayList<Usuario>();
        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {
            jpql = "select m from Usuario m where m.login = :pLogin";  
            Query query = em.createQuery(jpql);
            query.setParameter("pLogin", busca);  
            retorno = query.getResultList();            
            usuario = retorno.get(0);
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
        return usuario;
    }
    
    
    public Long count_tipo(String busca)
    {   
        String jpql;
        Long quantidade = null;
        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {
            jpql = "select count(m.tipo) from Usuario m where m.tipo = :pTipo";  
            Query query = em.createQuery(jpql); 
            query.setParameter("pTipo", busca);  
            quantidade = (Long) query.getSingleResult();
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
            return quantidade;
    }
    public List<Usuario> listar(String busca) {
        
        String jpql;
        List<Usuario> retorno = new ArrayList<Usuario>();
        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {
            if(busca.equals("") == true)
                jpql = "select m from Usuario m order by m.nome";
            else
                jpql = "select m from Usuario m where m.nome like :pBusca "
                        + "OR m.login like :pBusca order by m.nome";  

            TypedQuery<Usuario> query = em.createQuery(jpql,Usuario.class);
            if (busca.equals("") == false) 
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

    public Usuario login(String login, String senha)
    {
        String jpql;
        Usuario usuario = null;
        List<Usuario> retorno = new ArrayList<Usuario>();
        if(!em.isOpen())
        {
            em = new JPAUtil().getEntityManager();
        }
        else
            em.getTransaction().begin();
        try
        {
            jpql = "select m from Usuario m where m.login = :pLogin and m.senha = :pSenha";  
            TypedQuery<Usuario> query = em.createQuery(jpql,Usuario.class);
            query.setParameter("pLogin", login);
            query.setParameter("pSenha", senha);
            retorno = query.getResultList();
            usuario = retorno.get(0);          
            em.getTransaction().commit();
        } 
        catch(Exception e)
        {
            em.getTransaction().rollback();
            //System.out.println(e.getMessage());
        }
        finally
        {
            em.close();
        }
        return usuario;
    }
    
}
