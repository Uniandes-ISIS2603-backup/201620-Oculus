/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.oculus.audiovisuales.ejbs;

import co.edu.uniandes.oculus.audiovisuales.api.IEquipoLogic;
import co.edu.uniandes.oculus.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.ReservaEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.TipoEntity;
import co.edu.uniandes.oculus.audiovisuales.exceptions.BusinessLogicException;
import co.edu.uniandes.oculus.audiovisuales.persistence.EquipoPersistence;
import co.edu.uniandes.oculus.audiovisuales.persistence.TipoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 *
 * @author gc.andrade10
 */
@Stateless
@Default
public class EquipoLogic implements IEquipoLogic
{
    @Inject
    private EquipoPersistence persistence;
    
    @Inject
    private TipoPersistence tipoPersistence;
    
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
        //persistence.create(e);
        if(e.getId()!=null)
        {
            if(persistence.find(e.getId()) != null)
            {
                throw new BusinessLogicException("Ya existe un equipo con ese id");
            }
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
    
    @Override
    public List<TipoEntity> getTipos()
    {
        return persistence.darTipos();
    }
    
    @Override
    public EquipoEntity getEquipoByIdPuntoDeAtencion(Long idPuntoDeAtencion, Long idEquipo)
    {
        return persistence.buscarEquipoPorIdPuntoDeAtencion(idPuntoDeAtencion, idEquipo);
    }
    
    @Override
    public ReservaEntity getReservaActiva(long idEquipo)
    {
        ReservaEntity r = null;
        List<ReservaEntity> rs = persistence.getReservaActiva(idEquipo);
        if(rs.size()>0)
            r=rs.get(0);
        return r;
    }
    
    @Override
    public void crearTipos()
    {
        persistence.crearTipos();
    }
    
    @Override
    public TipoEntity getTipo(Long id)
    {
        return tipoPersistence.find(id);
    }
    
    @Override
    public void truncate()
    {
        persistence.truncate();
        tipoPersistence.truncate();
    }
    
    
}
