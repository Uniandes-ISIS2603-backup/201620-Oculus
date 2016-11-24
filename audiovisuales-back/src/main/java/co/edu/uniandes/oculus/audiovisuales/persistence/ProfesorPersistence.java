/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.oculus.audiovisuales.persistence;

import co.edu.uniandes.oculus.audiovisuales.entities.ProfesorEntity;
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
 * @author fa.lopez10
 */
@Stateless
public class ProfesorPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ProfesorPersistence.class.getName());
    
    @PersistenceContext(unitName = "OculusPU")
    protected EntityManager em;
    
    public ProfesorEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando Profesor con id={0}", id);
        return em.find(ProfesorEntity.class, id);
    }
    
    public ProfesorEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando profesor con name = {0}", name);
        TypedQuery<ProfesorEntity> q
                = em.createQuery("select u from ProfesorEntity u where u.name = :name", ProfesorEntity.class);
        q = q.setParameter("name", name);
        
       List<ProfesorEntity> profesoresSimilarName = q.getResultList();
        if (profesoresSimilarName.isEmpty() ) {
            return null; 
        } else {
            return profesoresSimilarName.get(0);
        }
    }
    
    public ProfesorEntity create(ProfesorEntity entity)
    {
        LOGGER.info("Creando un profesor nuevo");
        em.persist(entity);
        LOGGER.info("Profesor creado");
        return entity;
    }
    
    public List<ProfesorEntity> findAll() {
        LOGGER.info("Consultando todos los profesores");
        Query q = em.createQuery("select u from ProfesorEntity u");
        return q.getResultList();
    }
    
    public ProfesorEntity update(ProfesorEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando profesor con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando profesor con id={0}", id);
        ProfesorEntity entity = em.find(ProfesorEntity.class, id);
        em.remove(entity);
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
