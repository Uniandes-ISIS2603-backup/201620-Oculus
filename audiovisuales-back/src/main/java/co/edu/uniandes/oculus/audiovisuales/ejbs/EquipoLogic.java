/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.oculus.audiovisuales.ejbs;

import co.edu.uniandes.oculus.audiovisuales.api.IEquipoLogic;
import co.edu.uniandes.oculus.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.oculus.audiovisuales.exceptions.BusinessLogicException;
import co.edu.uniandes.oculus.audiovisuales.persistence.EquipoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author gc.andrade10
 */
@Stateless
public class EquipoLogic implements IEquipoLogic
{
    @Inject
    private EquipoPersistence persistence;
    @Override
    public List<EquipoEntity> getEquipos()
    {
        return persistence.findAll();
    }
    
    @Override
    public EquipoEntity getEquipo(Long id)
    {
        return persistence.find(id);
    }
    
    @Override
    public EquipoEntity getEquipoByName(String name)
    {
        return persistence.findByName(name);
    }
    
    /**
     *
     * @param e
     * @return
     * @throws BusinessLogicException
     */
    @Override
    public EquipoEntity createEquipo(EquipoEntity e) throws BusinessLogicException
    {
        EquipoEntity equipo = persistence.findByName(e.getName());
        if(equipo != null)
        {
            throw new BusinessLogicException("Ya existe un equipo con ese nombre");
        }
        return persistence.create(e);
    }
    
    @Override
    public EquipoEntity updateEquipo(EquipoEntity e)
    {
        return persistence.update(e);
    }
    
    @Override
    public void deleteEquipo(Long id)
    {
        persistence.delete(id);
    }
    
    @Override
    public List<EquipoEntity> getEquiposByIdPuntoDeAtencion(Long idPuntoDeAtencion)
    {
        return persistence.buscarEquiposPorIdPuntoDeAtencion(idPuntoDeAtencion);
    }
    
}
