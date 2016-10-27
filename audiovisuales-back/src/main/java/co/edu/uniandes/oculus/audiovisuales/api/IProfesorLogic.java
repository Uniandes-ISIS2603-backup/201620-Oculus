/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.api;

import co.edu.uniandes.oculus.audiovisuales.entities.ProfesorEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.ReservaEntity;
import co.edu.uniandes.oculus.audiovisuales.exceptions.BusinessLogicException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fa.lopez10
 */
public interface IProfesorLogic {
    public List<ProfesorEntity> getProfesores();
    public ProfesorEntity getProfesor(Long profesorid);
    public ProfesorEntity getProfesorByName(String name);
    public ProfesorEntity createProfesor(ProfesorEntity entity) throws BusinessLogicException ;
    public ProfesorEntity updateProfesor(ProfesorEntity entity);
    public List<ReservaEntity> listReservasRangoFechas(long profesorId, Date fecha1, Date fecha2) throws BusinessLogicException;
    public void deleteProfesor(Long id);
    
}
