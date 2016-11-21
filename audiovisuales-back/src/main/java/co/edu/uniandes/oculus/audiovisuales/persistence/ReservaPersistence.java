/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.persistence;

import co.edu.uniandes.oculus.audiovisuales.entities.EquipoEntity;
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
 * @author fa.lopez10
 */
@Stateless
public class ReservaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ReservaPersistence.class.getName());
    
    @PersistenceContext(unitName = "OculusPU")
    protected EntityManager em;
    
    public ReservaEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando Reserva con id={0}", id);
        return em.find(ReservaEntity.class, id);
    }
    
    public ReservaEntity create(ReservaEntity entity)
    {
        LOGGER.info("Creando una reserva nueva");
        em.persist(entity);
        LOGGER.info("Reserva creada");
        return entity;
    }
    
    public List<ReservaEntity> findAll() {
        LOGGER.info("Consultando todas los reservaes");
        Query q = em.createQuery("select u from ReservaEntity u");
        return q.getResultList();
    }
    
    public ReservaEntity update(ReservaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando reserva con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    public ReservaEntity cancelar(Long id) {
        LOGGER.log(Level.INFO, "Cancelando reserva con id={0}", id);
        ReservaEntity entity = em.find(ReservaEntity.class, id);
        entity.setEstado(ReservaEntity.RESERVA_CANCELADA);
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando reserva con id={0}", id);
        ReservaEntity entity = em.find(ReservaEntity.class, id);
        em.remove(entity);
    }
    
    public List<ReservaEntity> findAllCanceladas() 
    {
        LOGGER.info("Consultando todas los reservaes");
        Query q = em.createQuery("select u from ReservaEntity u where u.estado = cancelado");
        q.setParameter("cancelado", ReservaEntity.RESERVA_CANCELADA);
        return q.getResultList();
    }
    
    public List<ReservaEntity> findAllPendientes() 
    {
        LOGGER.info("Consultando todas los reservaes");
        Query q = em.createQuery("select u from ReservaEntity u where u.estado = pendiente");
        q.setParameter("pendiente", ReservaEntity.RESERVA_PENDIENTE);
        return q.getResultList();
    }
    
    public List<ReservaEntity> buscarReservasPorIdProfesor(Long id1)
    {
        LOGGER.log(Level.INFO, "buscarReservasPorIdProfesor con el id dado: "+id1);
        Query q = em.createQuery("SELECT u FROM ReservaEntity u WHERE  u.profesor.id = :id");
        q = q.setParameter("id", id1);
        return q.getResultList();
    }
    
    public ReservaEntity buscarReservaPorIdProfesor(Long idProfesor, Long idReserva) 
    {
         LOGGER.log(Level.INFO, "Consultar reserva del profesor con el id dado: "+idProfesor +"y reserva con id:"+idReserva);
        TypedQuery<ReservaEntity> q = em.createQuery("SELECT u FROM ReservaEntity u WHERE  u.profesor.id = :id AND u.id=:ide",ReservaEntity.class);
        q = q.setParameter("id", idProfesor);
        q = q.setParameter("ide", idReserva);
        return q.getSingleResult();
    }
}
