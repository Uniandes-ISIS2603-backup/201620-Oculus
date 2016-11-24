/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.ejbs;
import co.edu.uniandes.oculus.audiovisuales.api.IPuntoDeAtencionLogic;
import co.edu.uniandes.oculus.audiovisuales.entities.PuntoDeAtencionEntity;
import co.edu.uniandes.oculus.audiovisuales.exceptions.BusinessLogicException;
import javax.ejb.Stateless;
import co.edu.uniandes.oculus.audiovisuales.persistence.PuntoDeAtencionPersistence;
import java.util.List;
import javax.inject.Inject;
/**
 *
 * @author am.espinosa11
 */
@Stateless
public class PuntoDeAtencionLogic implements IPuntoDeAtencionLogic
{
    @Inject
    private PuntoDeAtencionPersistence persistence;

    @Override
    public List<PuntoDeAtencionEntity> getPuntosDeAtencion() 
    {
        return persistence.findAll(); 
    }

    @Override
    public PuntoDeAtencionEntity getPuntoDeAtencion(Long id) 
    {
        return persistence.find(id); 
    }

    @Override
    public PuntoDeAtencionEntity createPuntoDeAtencion(PuntoDeAtencionEntity puntoDeAtencion) throws BusinessLogicException
    {
      PuntoDeAtencionEntity punto = persistence.findByName(puntoDeAtencion.getName()); 
      if(punto != null)
      {
          throw new BusinessLogicException("Ya existe un punto de atención con esa ubicación");
      }
      else
      {
          persistence.create(puntoDeAtencion);
      }
      return puntoDeAtencion;
    }

    @Override
    public void deletePuntoDeAtencion(Long id) 
    {
        persistence.delete(id); 
    }

    @Override
    public PuntoDeAtencionEntity updatePuntoDeAtencion(PuntoDeAtencionEntity puntoDeAtencion)
    {
        return persistence.update(puntoDeAtencion); 
    }

    @Override
    public PuntoDeAtencionEntity getByName(String ubicacion)
    {
        return persistence.findByName(ubicacion); 
       
    }
    
    @Override
    public void truncate()
    {
        persistence.truncate();
    }
}
