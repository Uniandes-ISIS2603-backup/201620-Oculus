package co.edu.uniandes.oculus.audiovisuales.persistence;

import co.edu.uniandes.oculus.audiovisuales.entities.AdministradorEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.ProfesorEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.PuntoDeAtencionEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.ReservaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
    
    /**
     * encontrar administrador por un id
     * @param id identificador del administrador
     * @return administrador con el id dado.
     */
    public AdministradorEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando Administrador con id={0}", id);
        return em.find(AdministradorEntity.class, id);
    }
    
    /**
     * da todos los administradores de la base de datos
     * @return devuelve todos los administradores
     */
    public List<AdministradorEntity> findAll() {
        LOGGER.info("Consultando todos los Administradores");
        Query q = em.createQuery("select u from AdministradorEntity u");
        return q.getResultList();
    }
    
    /**
     * crear un administrador
     * @param adminEntity
     * @return retorna el administrador creado
     */
    public AdministradorEntity create (AdministradorEntity adminEntity)
    {
        LOGGER.info("Creando un administrador nuevo");
        em.persist(adminEntity);
        LOGGER.info("Administrador creado");
        return adminEntity;
    }
    
    /**
     * Modificar un administrador
     * @param adminEntity administrador a modificar
     * @return el administrador modificado
     */
    public AdministradorEntity update (AdministradorEntity adminEntity)
    {
        LOGGER.log(Level.INFO, "Actualizando Administrador con id={0}", adminEntity.getId());
        return em.merge(adminEntity);
    }
    
    /**
     * Borrar un adminitrador dado su id
     * @param id identificador del administrador
     */
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando Administrador con id={0}", id);
        AdministradorEntity entity = em.find(AdministradorEntity.class, id);
        em.remove(entity);
    }
    
    /**
     * buscar un administrador por el nombre
     * @param name nombre del administrador a buscar
     * @return el aministrador con el nombre dado
     */
    public AdministradorEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando el Administrador con name = {0}", name);
        TypedQuery<AdministradorEntity> q = em.createQuery("select u from AdministradorEntity u where u.name = :name", AdministradorEntity.class);
        q = q.setParameter("name", name);
        
        List<AdministradorEntity> administradoresConNombreIgual = q.getResultList();
        if (administradoresConNombreIgual.isEmpty() ) {
            return null;
        } else {
            return administradoresConNombreIgual.get(0);
        }
    }
    
    public void truncate()
    {
        List a = findAll();
        for (Object object : a) 
        {
            em.remove(object);
        }
    }
}
