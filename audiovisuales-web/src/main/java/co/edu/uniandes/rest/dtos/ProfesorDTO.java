/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.dtos;

import java.util.ArrayList;

/**
 * Class that implements the teacher that will provide the equipment of the order
 * @author ac.fandino10
 * @author fa.lopez10
 */
public class ProfesorDTO
{
    private int id;
    private String nombre;
    private String login;
    private int codigo;
    private ArrayList<ReservaDTO> reservas;
    
    /**
     * Constructor por defecto
     */
    public ProfesorDTO()
    {    
    }
    
    /**
     * Constructor con parametros
     * @param id
     * @param nombre
     * @param login 
     * @param codigo 
     */
    public ProfesorDTO(int id, String nombre, String login, int codigo)
    {
        super();
        this.id=id;
        this.nombre=nombre;
        this.login=login;
        this.codigo=codigo;
    }
    
    /**
     * Gets the id of the teacher
     * @return id
     */
    public int getId()
    {
        return this.id;
    }
    /**
     * Sets the id to the teacher
     * @param id 
     */
    public void setId(int id)
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