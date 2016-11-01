/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.ejbs;

import co.edu.uniandes.oculus.audiovisuales.api.IAdministradorLogic;
import co.edu.uniandes.oculus.audiovisuales.entities.AdministradorEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.oculus.audiovisuales.exceptions.BusinessLogicException;
import co.edu.uniandes.oculus.audiovisuales.persistence.AdministradorPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * capa logica que maneja un Administrador
 * NOTA: DEBE ESTAR SIN ESTADO ESTA CLASE!!!
 * @author Sneider Velandia G
 */
@Stateless
public class AdministradorLogic implements IAdministradorLogic
{
    @Inject
    private AdministradorPersistence persistence;
    
    /**
     * obtiene la lista de los registros de Administrador
     * @return lista de OBJETOS de tipo Administrador
     */
    @Override
    public List<AdministradorEntity> getAdministradores()
    {
        return persistence.findAll();
    }
    
    /**
     * Obtiene los datos de una instancia de Administrador a partir de su ID.
     * @param id Identificador de la instancia a consultar
     * @return Instancia de AdministradorEntity con los datos del Administrador consultado.
     */
    public AdministradorEntity getAdministrador(Long id) 
    {
        return persistence.find(id);
    }
    
    /**
     * Se encarga de crear un Administrador en la base de datos.
     * @param adminEntity Objeto de AdministradorEntity con los datos nuevos
     * @return Objeto de AdministradorEntity con los datos nuevos y su ID.
     * @throws co.edu.uniandes.oculus.audiovisuales.exceptions.BusinessLogicException
     */
    @Override
    public AdministradorEntity createAdministrador(AdministradorEntity adminEntity) throws BusinessLogicException
    {
        AdministradorEntity yaExiste = getAdministradorByName(adminEntity.getName());
        if (yaExiste != null) 
        {
            // se debe generar una exception si ya existe el administrador
            throw new BusinessLogicException("Ya existe un Administrador con ese nombre");
        }
        //si no existe lo crea 
        else
        {
            persistence.create(adminEntity);
        }
        return adminEntity;
    }

    /**
     * Actualiza la información de una instancia de Administrador.
     * @param adminEntity Instancia de AdministradorEntity con los nuevos datos.
     * @return Instancia de AdministradorEntity con los datos actualizados.
     */
    @Override
    public AdministradorEntity updateAdministrador(AdministradorEntity adminEntity) 
    {
        return persistence.update(adminEntity);
    }

    /**
     * Elimina una instancia de Administrador de la base de datos.
     * @param id Identificador de la instancia a eliminar.
     */
    @Override
    public void deleteAdministrador(Long id) 
    {
        persistence.delete(id);
    }

    @Override
    public AdministradorEntity getAdministradorByName(String name) 
    {
        return persistence.findByName(name);
    }

}
