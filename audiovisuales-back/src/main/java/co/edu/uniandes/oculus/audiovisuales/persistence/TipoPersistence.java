/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.oculus.audiovisuales.persistence;

import co.edu.uniandes.oculus.audiovisuales.entities.TipoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gc.andrade10
 */
@Stateless
public class TipoPersistence
{
    
    private static final Logger LOGGER = Logger.getLogger(TipoPersistence.class.getName());
    
    @PersistenceContext(unitName = "OculusPU")
    protected EntityManager em;
    
    public TipoEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando Tipo con id={0}", id);
        return em.find(TipoEntity.class, id);
    }
    
    public TipoEntity create(TipoEntity entity)
    {
        LOGGER.info("Creando un tipo nuevo");
        em.persist(entity);
        LOGGER.info("Tipo creado");
        return entity;
    }
    
    public List<TipoEntity> findAll()
    {
        LOGGER.info("Consultando todos los tipos");
        Query consulta = em.createQuery("SELECT u FROM TipoEntity u");
        return consulta.getResultList();
    }
    
    
}
