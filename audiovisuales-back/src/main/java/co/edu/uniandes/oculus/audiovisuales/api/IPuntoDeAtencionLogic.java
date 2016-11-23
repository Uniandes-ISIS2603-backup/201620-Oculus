/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.api;
import co.edu.uniandes.oculus.audiovisuales.entities.PuntoDeAtencionEntity;
import co.edu.uniandes.oculus.audiovisuales.exceptions.BusinessLogicException;
import java.util.List;
/**
 *
 * @author am.espinosa11
 */
public interface IPuntoDeAtencionLogic 
{
         
    public List<PuntoDeAtencionEntity> getPuntosDeAtencion();
    
    public PuntoDeAtencionEntity getPuntoDeAtencion(Long id);
    
    public PuntoDeAtencionEntity createPuntoDeAtencion(PuntoDeAtencionEntity puntoDeAtencion) throws BusinessLogicException;
    
    public void deletePuntoDeAtencion(Long id);
    
    public PuntoDeAtencionEntity updatePuntoDeAtencion(PuntoDeAtencionEntity puntoDeAtencion);
    
    public PuntoDeAtencionEntity getByName(String ubicacion);
}
