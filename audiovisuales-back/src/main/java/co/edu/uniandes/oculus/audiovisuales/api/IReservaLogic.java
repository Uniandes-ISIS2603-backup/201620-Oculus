/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.api;

import co.edu.uniandes.oculus.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.ReservaEntity;
import co.edu.uniandes.oculus.audiovisuales.exceptions.BusinessLogicException;
import java.util.List;

/**
 *
 * @author fa.lopez10
 */
public interface IReservaLogic {
    public List<ReservaEntity> getReservas();
    public ReservaEntity getReservaByIdProfesor(Long idProfesor, Long idReserva);
    public List<ReservaEntity> getReservasByIdProfesor(Long idProfesor);
    public ReservaEntity getReserva(Long reservaid);
    public ReservaEntity createReserva(Long profesorid, ReservaEntity entity) throws BusinessLogicException ;
    public ReservaEntity updateReserva(Long profesorid, ReservaEntity entity);
    public ReservaEntity cancelarReserva(Long reservaid);
    public void deleteReserva(Long id);
    public EquipoEntity getEquipo(Long reservaId);
    
    public List<ReservaEntity> getReservasCanceladas(Long paId);
    public List<ReservaEntity> getReservasPendientes(Long paId);

    public void devolver(Long idEquipo, ReservaEntity r);
}
