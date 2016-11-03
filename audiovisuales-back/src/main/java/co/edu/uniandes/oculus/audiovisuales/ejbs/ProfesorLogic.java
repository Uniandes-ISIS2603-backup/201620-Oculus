/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.ejbs;

import co.edu.uniandes.oculus.audiovisuales.api.IProfesorLogic;
import co.edu.uniandes.oculus.audiovisuales.entities.ProfesorEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.ReservaEntity;
import co.edu.uniandes.oculus.audiovisuales.exceptions.BusinessLogicException;
import co.edu.uniandes.oculus.audiovisuales.persistence.ProfesorPersistence;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author fa.lopez10
 */
@Stateless
public class ProfesorLogic implements IProfesorLogic{

    @Inject
    private ProfesorPersistence persistence;
    
    @Override
    public List<ProfesorEntity> getProfesores() {
        return persistence.findAll();
    }

    @Override
    public ProfesorEntity getProfesor(Long profesorid) {
        return persistence.find(profesorid);
    }
    
    @Override
    public ProfesorEntity getProfesorByName(String name) {
        return persistence.findByName(name);
    }

    @Override
    public ProfesorEntity createProfesor(ProfesorEntity entity) throws BusinessLogicException {
        ProfesorEntity nameAlreadyExist = getProfesorByName(entity.getName());
        if ( nameAlreadyExist != null) {
            throw new BusinessLogicException("Ya existe un profesor con ese nombre" );
        } else
        {
            persistence.create(entity);
        }
        return entity;
    }

    @Override
    public ProfesorEntity updateProfesor(ProfesorEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteProfesor(Long id) {
        persistence.delete(id);
    }

    @Override
    public List<ReservaEntity> listReservasRangoFechas(long profesorId, Date fecha1, Date fecha2) throws BusinessLogicException {
        if(fecha1.after(fecha2))
            throw new BusinessLogicException("Rango de fechas no valido");
        return persistence.find(profesorId).getReservasRangoFechas(fecha1, fecha2);
    }

    
    
    
}

