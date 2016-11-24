/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.api;

import co.edu.uniandes.oculus.audiovisuales.entities.AdministradorEntity;
import co.edu.uniandes.oculus.audiovisuales.exceptions.BusinessLogicException;
import java.util.List;

/**
 *
 * @author Sneider Velandia G
 */
public interface IAdministradorLogic 
{
    public List<AdministradorEntity> getAdministradores();

    public AdministradorEntity getAdministrador(Long id);

    public AdministradorEntity getAdministradorByName(String name);
    //este metodo genera una exception de validacion con la logica del negocio 
    public AdministradorEntity createAdministrador(AdministradorEntity entity)throws BusinessLogicException ;

    public AdministradorEntity updateAdministrador(AdministradorEntity entity);

    public void deleteAdministrador(Long id);

    public void truncate();

    
    
}
