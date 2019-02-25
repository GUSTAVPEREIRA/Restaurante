/**
 * 
 */
package estagio.dao;

/**
 * @author Gabriel L. P. Abreu
 *
 */
public interface GenericDAOInterface<Template> {

    public Template persist(Template obj);

    public Template merge(Template obj);
    
    public Template delete(Template obj);

    public Template findById(Long id);

    //public List<T> listAll();
    
}