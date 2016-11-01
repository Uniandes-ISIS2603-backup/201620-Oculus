/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.dtos;

import co.edu.uniandes.oculus.audiovisuales.entities.ProfesorEntity;
import java.util.ArrayList;

/**
 * Class that implements the teacher that will provide the equipment of the order
 * @author fa.lopez10
 */
public class ProfesorDTO
{
    private Long id;
    private String nombre;
    private String login;
    private int codigo;
    
    /**
     * Constructor por defecto
     */
    public ProfesorDTO()
    {    
    }
    
    /**
     * Constructor con parametros
     * @param entity entidad de la que se traen los atributos para el DTO
     */
    public ProfesorDTO(ProfesorEntity entity)
    {
        if(entity != null)
        {
            this.id = entity.getId();
            this.nombre = entity.getName();
            this.login = entity.getLogin();
            this.codigo = entity.getCodigo();
        }
    }
    
    /**
     * 
     * @return profeNuevo la entidad creada
     */
    public ProfesorEntity toEntity()
    {
        ProfesorEntity profeNuevo = new ProfesorEntity();
        profeNuevo.setId(id);
        profeNuevo.setName(nombre);
        profeNuevo.setLogin(login);
        profeNuevo.setCodigo(codigo);
        return profeNuevo;
    }
    
    /**
     * Gets the id of the teacher
     * @return id
     */
    public Long getId()
    {
        return this.id;
    }
    /**
     * Sets the id to the teacher
     * @param id 
     */
    public void setId(Long id)
    {
        this.id = id;
    }
    
    /**
     * Returns the name of the teacher
     * @return nombre
     */
    public String getNombre()
    {
        return this.nombre;
    }
    
    /**
     * Sets the name of the teacher
     * @param nombre 
     */
    public void setNombre(String nombre)
    {
      this.nombre = nombre;  
    }
    
    /**
     * Gives the login info (password) of the teacher
     * @return login
     */
    public String getLogin(){
        return this.login;
    }
    
    /**
     * Changes the login info of the teacher
     * @param login 
     */
    public void setLogin(String login){
        this.login=login;
    }
    
    /**
     * Gets the codigo of the teacher
     * @return codigo
     */
    public int getCodigo()
    {
        return this.codigo;
    }
    /**
     * Sets the codigo to the teacher
     * @param codigo 
     */
    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }
    
    public String toString() {
    	return "{ id : " + getId() + ", nombre : \"" + getNombre()+", login : \"" + getLogin()+", codigo : \"" + getCodigo() + "\" }" ;  
    }
}