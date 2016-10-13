package co.edu.uniandes.oculus.audiovisuales.persistence;

import co.edu.uniandes.oculus.audiovisuales.entities.AdministradorEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Sneider Velandia G
 */
@Stateless
public class AdministradorPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(AdministradorPersistence.class.getName());
    
    // "bolsa" donde estaran los obj1, obj2.... (Administrador) que son parte de la base de datos en forma de fila
    @PersistenceContext(unitName = "OculusPU")
    protected EntityManager em;
    
    /// CRUD ///

    //devuelve solo un administrador
    public AdministradorEntity find(Long id) 
    {
        LOGGER.log(Level.INFO, "Consultando Administrador con id={0}", id);
        return em.find(AdministradorEntity.class, id);
    }
    
    //devuelve todos los administradores
    public List<AdministradorEntity> findAll() {
        LOGGER.info("Consultando todos los Administradores");
        Query q = em.createQuery("select u from AdministradorEntity u");
        return q.getResultList();
    }
         
    public AdministradorEntity create (AdministradorEntity entity)
    {
        LOGGER.info("Creando un administrador nuevo");
        em.persist(entity);
        LOGGER.info("Administrador creado");
        return entity;
    }
    
    public AdministradorEntity update (AdministradorEntity entity)
    {
        LOGGER.log(Level.INFO, "Actualizando Administrador con id={0}", entity.getId());
        return em.merge(entity);
    }
      
    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando Administrador con id={0}", id);
        AdministradorEntity entity = em.find(AdministradorEntity.class, id);
        em.remove(entity);
    }
}
