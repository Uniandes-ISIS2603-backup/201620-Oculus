/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.dtos;


import java.util.logging.Logger;
import co.edu.uniandes.rest.mocks.LocacionLogicMock;
import java.util.ArrayList;
import java.util.logging.Level;


/**
 *
 * @author am.espinosa11
 */
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class PuntoDeAtencionDTO 
{
    private String ubicacion;
    private ArrayList<EquipoDTO> equipos;
    private Long id;
    
    /**
     * Constructor por defecto
     */
    public PuntoDeAtencionDTO()
    {
        equipos = new ArrayList<EquipoDTO>();
    }
    /**
     * Constructor 
     * @param pUbicacion edificio de ubicacion
     */
    public PuntoDeAtencionDTO(Long pId,String pUbicacion) 
    {
      super();
      this.equipos = new ArrayList<EquipoDTO>();
      this.id = pId;
      this.ubicacion = pUbicacion;
    }
    
     private final static Logger logger = Logger.getLogger(LocacionLogicMock.class.getName());
     
     
     /**
     * @return ubicacion
     */
     public String getUbicacion()
     {
         return ubicacion;
     }
     
     /**
     * @param pUbicacion en la cual se encuentra
     */
     public void setUbicacion(String pUbicacion)
     {
         this.ubicacion = pUbicacion;
     }
     
     
     /**
     * @return id
     */
     public Long getId()
     {
         return this.id;
     }
     
      /**
     * @param pId asignada
     */
     public void setId(Long pId)
     {
         this.id = pId;
     }
      
         
     /**
     * @return ubicacion
     */
     public ArrayList getEquipos()
     {
         return equipos;
     }
     
     /**
     * @param pUbicacion en la cual se encuentra
     */
     public void setEquipos(ArrayList pEquipos)
     {
         this.equipos = pEquipos;
     }
     
     public EquipoDTO agregarEquipo(EquipoDTO e)
     {
         this.equipos.add(e);
         return e;
     } 
     
     /**
     * Convierte el objeto a una cadena
     * @return 
     */
    @Override
    public String toString() 
    {
        String formato="{ ubicacion : " + getUbicacion() +"}" ; 
    	logger.setLevel(Level.INFO);
        logger.info("toStrimg:"+ formato);
        return  formato;
        
    }
}
