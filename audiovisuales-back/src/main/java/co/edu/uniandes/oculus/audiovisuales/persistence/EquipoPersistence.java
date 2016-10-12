/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.oculus.audiovisuales.persistence;

import co.edu.uniandes.oculus.audiovisuales.entities.EquipoEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gc.andrade10
 */
@Stateless
public class EquipoPersistence
{
    
    private static final Logger LOGGER = Logger.getLogger(EquipoPersistence.class.getName());
    
    @PersistenceContext(unitName = "OculusPU")
    protected EntityManager em;
    
    public EquipoEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando Equipo con id={0}", id);
        return em.find(EquipoEntity.class, id);
    }
    
    public EquipoEntity create(EquipoEntity entity)
    {
        LOGGER.info("Creando un equipo nuevo");
        em.persist(entity);
        LOGGER.info("Equipo creado");
        return entity;
    }
    
}
