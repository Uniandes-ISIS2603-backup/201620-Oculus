/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.mocks;

/**
 * Mock del recurso Equipos (Mock del servicio REST)
 * @author am.espinosa11
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.edu.uniandes.rest.dtos.PuntoDeAtencionDTO;
import co.edu.uniandes.rest.exceptions.CityLogicException;
/**
 *
 * @author am.espinosa11
 */
public class PuntoDeAtencionLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(PuntoDeAtencionLogicMock.class.getName());

    // listado de los puntos de atención
    private static ArrayList<PuntoDeAtencionDTO> puntosDeAtencion;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public PuntoDeAtencionLogicMock() {

        if (puntosDeAtencion == null) {
            puntosDeAtencion = new ArrayList<>();
            puntosDeAtencion.add(new PuntoDeAtencionDTO(1L, "Mario Laserna"));
            puntosDeAtencion.add(new PuntoDeAtencionDTO(2L, "Julio Mario Santo Domingo"));
            puntosDeAtencion.add(new PuntoDeAtencionDTO(3L, "Aulas"));
        }

        // indica que se muestren todos los mensajes
        logger.setLevel(Level.INFO);

        // muestra información 
        logger.info("Inicializa la lista de puntos de atención");
        logger.info("puntos de atención" + puntosDeAtencion);
    }

    /**
     *
     * @return lista de puntos de atencion
     * @throws CityLogicException cuando no existe la lista en memoria
     */
    public List<PuntoDeAtencionDTO> getPuntosDeAtencion() throws CityLogicException {
        if (puntosDeAtencion == null) {
            logger.severe("Error interno: lista de Punto de atención no existe.");
            throw new CityLogicException("Error interno: lista de puntos de atencion no existe.");
        }

        logger.info("retornando todos los puntos de atencion");
        return puntosDeAtencion;
    }

    /**
     * Obtiene un punto de atencion
     *
     * @param id identificador del punto de atencion
     * @return punto encontrada
     * @throws CityLogicException cuando el punto de atencion no existe
     */
    public PuntoDeAtencionDTO getPuntoDeAtencion(Long id) throws CityLogicException {
        logger.info("recibiendo solicitud del punto de atencion con id " + id);

        for (PuntoDeAtencionDTO punto : puntosDeAtencion) {
            if (Objects.equals(punto.getId(), id)) {
                logger.info("retornando punto de atencion " + punto);
                return punto;
            }
        }

        logger.severe("No existe punto de atencion con ese id");
        throw new CityLogicException("No existe punto de atencion con ese id");
    }

    /**
     * Agrega un punto de atención a la lista.
     *
     * @param newPunto punto de atencion a adicionar
     * @throws CityLogicException cuando ya existe un punto de atencion con el id
     * suministrado
     * @return punto agregado
     */
    public PuntoDeAtencionDTO createPuntoDeAtencion(PuntoDeAtencionDTO newPunto) throws CityLogicException {
        logger.info("recibiendo solicitud de agregar punto de atencion " + newPunto);

      
       	if ( newPunto.getId() != null ) {
	    	// busca el equipo con el id suministrado
	       for (PuntoDeAtencionDTO puntoAtencion : puntosDeAtencion) {
                
                if (Objects.equals(puntoAtencion.getId(), newPunto.getId())) {
                    logger.severe("Ya existe un punto de atencion con ese id");
                    throw new CityLogicException("Ya existe un punto de atencion con ese id");
                };
                if (Objects.equals(puntoAtencion.getUbicacion(), newPunto.getUbicacion())) {
                    logger.severe("Ya existe un punto de atencion con esa ubicacion");
                    throw new CityLogicException("Ya existe un punto de atencion con esa ubicacion");
                }

            }
	        
	    // el nuevo equipo no tiene id ? 
    	} else {
            for (PuntoDeAtencionDTO puntoAtencion : puntosDeAtencion) {
                
                
                if (Objects.equals(puntoAtencion.getUbicacion(), newPunto.getUbicacion())) {
                    logger.severe("Ya existe un punto de atencion con esa ubicacion");
                    throw new CityLogicException("Ya existe un punto de atencion con esa ubicacion");
                }

            }
           
            logger.info("Generando id para el nuevo punto de aetncion");
            long newId = 1;
            for (PuntoDeAtencionDTO puntoAtencion : puntosDeAtencion) {
                if (newId <= puntoAtencion.getId()) {
                    newId = puntoAtencion.getId() + 1;
                }
            }
            newPunto.setId(newId);
        }

        
        logger.info("agregando punto de atencion " + newPunto);
        puntosDeAtencion.add(newPunto);
        return newPunto;
    
    }

    /**
     * Actualiza los datos de un punto de atencion
     *
     * @param id identificador del punto de atencion a modificar
     * @param punto punto de atencion a modificar
     * @return datos del punto de atencion modificado
     * @throws CityLogicException cuando no existe una editorial con el id
     * suministrado
     */
    public PuntoDeAtencionDTO updatePuntoDeAtencion(Long id, PuntoDeAtencionDTO punto) throws CityLogicException {
        logger.info("recibiendo solictud de modificar punto de atencion " + punto);

        
        for (PuntoDeAtencionDTO puntoAte : puntosDeAtencion) {
            if (Objects.equals(puntoAte.getId(), id)) {

                puntoAte.setId(punto.getId());
                puntoAte.setUbicacion(punto.getUbicacion());

                logger.info("Modificando punto de atencion " + puntoAte);
                return puntoAte;
            }
        }

        logger.severe("No existe un punto de atencion con ese id");
        throw new CityLogicException("No existe un punto de atencion con ese id");
    }

    /**
     * Elimina los datos de un punto de atencion
     *
     * @param id identificador del punto de atencion a eliminar
     * @throws CityLogicException cuando no existe un punto de atencion con el id
     * suministrado
     */
    public void deletePuntoDeAtencion(Long id) throws CityLogicException {
        logger.info("recibiendo solictud de eliminar punto de atencion con id " + id);

        for (PuntoDeAtencionDTO punto : puntosDeAtencion) {
            if (Objects.equals(punto.getId(), id)) {

                 logger.info("eliminando punto de atencion " + punto);
                puntosDeAtencion.remove(punto);
                return;
            }
        }


        logger.severe("No existe un punto de atencion con ese id");
        throw new CityLogicException("No existe un punto de con ese id");
    }
}

