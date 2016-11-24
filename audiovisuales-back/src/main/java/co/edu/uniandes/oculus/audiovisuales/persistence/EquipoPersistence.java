/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.oculus.audiovisuales.persistence;

import co.edu.uniandes.oculus.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.ReservaEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.TipoEntity;
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
    
    public EquipoEntity findByName(String name)
    {
        LOGGER.log(Level.INFO, "Consultando equipo con name = {0}", name);
        TypedQuery<EquipoEntity> q = em.createQuery(
                "SELECT u FROM EquipoEntity u WHERE u.name = :name",EquipoEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }
    
    public List<EquipoEntity> findAll()
    {
        LOGGER.info("Consultando todos los equipos");
        Query consulta = em.createQuery("SELECT u FROM EquipoEntity u");
        return consulta.getResultList();
    }
    
    public EquipoEntity create(EquipoEntity entity)
    {
        LOGGER.info("Creando un equipo nuevo");
        em.persist(entity);
        LOGGER.info("Equipo creado");
        return entity;
    }
    
    public EquipoEntity update(EquipoEntity entidad)
    {
        LOGGER.log(Level.INFO, "Actualizando equipo con id={0}",entidad.getId());
        return em.merge(entidad);
    }
    
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando el equipo con id={0}",id);
        EquipoEntity entidad = em.find(EquipoEntity.class, id);
        em.remove(entidad);
    }
    
    public List<EquipoEntity> buscarEquiposPorIdPuntoDeAtencion(Long id1)
    {
        LOGGER.log(Level.INFO, "buscarEquiposPorIdPuntoDeAtencionn con el id dado: "+id1);
        Query q = em.createQuery("SELECT u FROM EquipoEntity u WHERE  u.puntoDeAtencion.id = :id");
        q = q.setParameter("id", id1);
        return q.getResultList();
    }
    
    public List<TipoEntity> darTipos()
    {
        LOGGER.info("Consultando todos los equipos");
        Query consulta = em.createQuery("SELECT u FROM TipoEntity u");
        return consulta.getResultList();
    }
    
    public EquipoEntity buscarEquipoPorIdPuntoDeAtencion(Long idPuntoDeAtencion, Long idEquipo) 
    {
         LOGGER.log(Level.INFO, "Consultar equipo del Punto de atención con el id dado: "+idPuntoDeAtencion +"y el equipo con id:"+idEquipo);
        TypedQuery<EquipoEntity> q = em.createQuery("SELECT u FROM EquipoEntity u WHERE  u.puntoDeAtencion.id = :id AND u.id=:ide",EquipoEntity.class);
        q = q.setParameter("id", idPuntoDeAtencion);
        q = q.setParameter("ide", idEquipo);
        return q.getSingleResult();
    }

    public List<ReservaEntity> getReservaActiva(long idEquipo) 
    {
        LOGGER.log(Level.INFO, "Consultar reserva activa del equipo con id:"+idEquipo);
        TypedQuery<ReservaEntity> q = em.createQuery("SELECT u FROM ReservaEntity u WHERE  u.equipo.id = :id  AND u.estado = :activo ",ReservaEntity.class);
        q = q.setParameter("id", idEquipo);
        q = q.setParameter("activo",ReservaEntity.RESERVA_ACTIVA);
        return q.getResultList();
    }

    public void crearTipos() 
    {
        LOGGER.log(Level.INFO, "Crear tipo 1");
        TipoEntity t = new TipoEntity();
        t.setId(1L);
        t.setName("Computador");
        em.persist(t);
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
