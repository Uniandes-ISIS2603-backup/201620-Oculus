package co.edu.uniandes.oculus.audiovisuales.persistence;

import co.edu.uniandes.oculus.audiovisuales.entities.AdministradorEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
    public AdministradorEntity find(Long id) 
    {
        LOGGER.log(Level.INFO, "Consultando Administrador con id={0}", id);
        return em.find(AdministradorEntity.class, id);
    }
    
}
