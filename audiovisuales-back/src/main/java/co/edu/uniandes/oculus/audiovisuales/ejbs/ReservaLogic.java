/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.oculus.audiovisuales.ejbs;

import co.edu.uniandes.oculus.audiovisuales.api.IProfesorLogic;
import co.edu.uniandes.oculus.audiovisuales.api.IReservaLogic;
import co.edu.uniandes.oculus.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.ProfesorEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.ReservaEntity;
import co.edu.uniandes.oculus.audiovisuales.exceptions.BusinessLogicException;
import co.edu.uniandes.oculus.audiovisuales.persistence.ReservaPersistence;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author fa.lopez10
 */
@Stateless
public class ReservaLogic implements IReservaLogic{
    
    @Inject
    private ReservaPersistence persistence;
    
    @Inject
    private IProfesorLogic profesorLogic;
    
    @Override
    public List<ReservaEntity> getReservas(Long profesorId) {
        return persistence.findAll();
    }
    
    @Override
    public ReservaEntity getReserva(Long reservaid) {
        return persistence.find(reservaid);
    }
    
    @Override
    public ReservaEntity createReserva(Long profesorid, ReservaEntity entity) /*throws BusinessLogicException */{
        ProfesorEntity  profesor = profesorLogic.getProfesor(profesorid);
        entity.setProfesor(profesor);
        /*if(entity.getFecha().before(new Date()))
            throw new BusinessLogicException("La fecha de la reserva debe se posterior a la actual");*/
        return entity = persistence.create(entity);
    }
    
    @Override
    public ReservaEntity updateReserva(Long profesorid, ReservaEntity entity) {
        ProfesorEntity profesor = profesorLogic.getProfesor(profesorid);
        entity.setProfesor(profesor);
        return persistence.update(entity);
    }
    
    @Override
    public ReservaEntity cancelarReserva(Long id) {
        ReservaEntity old = getReserva(id);
        return persistence.cancelar(id);
    }
    
    @Override
    public void deleteReserva(Long id) {
        ReservaEntity old = getReserva(id);
        persistence.delete(old.getId());
    }
    
    @Override
    public EquipoEntity getEquipo(Long reservaId) {
        return persistence.find(reservaId).getEquipo();
    }

    @Override
    public void devolver(Long idEquipo , ReservaEntity r) 
    {
        r.setEstado("Reserva Activa");
        persistence.update(r);
    }
    
}
