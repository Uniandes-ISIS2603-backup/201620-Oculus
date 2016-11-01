/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.test.logic;

import co.edu.uniandes.oculus.audiovisuales.api.IReservaLogic;
import co.edu.uniandes.oculus.audiovisuales.ejbs.*;
import co.edu.uniandes.oculus.audiovisuales.entities.ProfesorEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.PuntoDeAtencionEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author fa.lopez10
 */
public class ReservaLogicTest {
    
    public ReservaLogicTest() {
    }
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IReservaLogic reservaLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<ReservaEntity> reservaData = new ArrayList<ReservaEntity>();
    private List<ProfesorEntity> profesorData = new ArrayList<ProfesorEntity>();
    
    @Before
    public void setUp() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData()
    {
        em.createQuery("DELETE FROM ProfesorEntity").executeUpdate();
        em.createQuery("DELETE FROM ReservaEntity").executeUpdate();
        em.createQuery("DELETE FROM EquipoEntity").executeUpdate();
    }
    
    private void insertData()
    {
        for (int i = 0; i < 3; i++) {
            ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);
            
            em.persist(entity);
            reservaData.add(entity);
        }
        
        for (int i = 0; i < 3; i++)
        {
            ProfesorEntity profe = factory.manufacturePojo(ProfesorEntity.class);
            em.persist(profe);
            profesorData.add(profe);
        }
        
        
    }
    
    
    /**
     * Test of getReservas method, of class ReservaLogic.
     */
    @Test
    public void testGetReservas() throws Exception {
        List <ReservaEntity> list = reservaLogic.getReservas(profesorData.get(0).getId());
        Assert.assertEquals(reservaData.size(), list.size());
        for (ReservaEntity  entidad :list)
        {
            boolean encontrado = false;
            for (ReservaEntity entidadAlmacenada : reservaData)
            {
                if(entidad.getId().equals(entidadAlmacenada.getId()))
                {
                    encontrado=true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }

    /**
     * Test of getReserva method, of class ReservaLogic.
     */
    @Test
    public void testGetReserva() throws Exception {
        ReservaEntity entidad = reservaData.get(0);
        ReservaEntity resultado = reservaLogic.getReserva(entidad.getId());
        Assert.assertNotNull(resultado);
        Assert.assertEquals(entidad.getEstado(), resultado.getEstado());
        Assert.assertEquals(entidad.getFecha(), resultado.getFecha());
        Assert.assertEquals(entidad.getId(), resultado.getId());
    }

    /**
     * Test of createReserva method, of class ReservaLogic.
     */
    @Test
    public void testCreateReserva() throws Exception {
        ReservaEntity nuevaEntidad = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity resultado = reservaLogic.createReserva(profesorData.get(0).getId(),nuevaEntidad);
        Assert.assertNotNull(resultado);
        reservaData.add(resultado);
        ReservaEntity entidad = em.find(ReservaEntity.class, resultado.getId());
        Assert.assertEquals(nuevaEntidad.getFecha(),entidad.getFecha());
        Assert.assertEquals(nuevaEntidad.getEstado(),entidad.getEstado());
        Assert.assertEquals(nuevaEntidad.getId(),entidad.getId());
    }

    /**
     * Test of updateReserva method, of class ReservaLogic.
     */
    @Test
    public void testUpdateReserva() throws Exception {
        ReservaEntity entidad = reservaData.get(0);
        ReservaEntity entidadGenerada = factory.manufacturePojo(ReservaEntity.class);
        entidadGenerada.setId(entidad.getId());
        reservaLogic.updateReserva(profesorData.get(0).getId(),entidadGenerada);
        ReservaEntity r = em.find(ReservaEntity.class, entidad.getId());
        Assert.assertEquals(entidadGenerada.getEstado(), r.getEstado());
        Assert.assertEquals(entidadGenerada.getId(), r.getId());
        Assert.assertEquals(entidadGenerada.getFecha(), r.getFecha());
    }

    /**
     * Test of cancelarReserva method, of class ReservaLogic.
     */
    @Test
    public void testCancelarReserva() throws Exception {
        ReservaEntity entidad = reservaData.get(0);
        ReservaEntity entidadGenerada = factory.manufacturePojo(ReservaEntity.class);
        entidadGenerada.setId(entidad.getId());
        entidadGenerada.setEstado(ReservaEntity.RESERVA_CANCELADA);
        reservaLogic.cancelarReserva(entidad.getId());
        ReservaEntity r = em.find(ReservaEntity.class, entidad.getId());
        Assert.assertEquals(entidadGenerada.getEstado(), r.getEstado());
        Assert.assertEquals(entidadGenerada.getId(), r.getId());
        Assert.assertEquals(entidadGenerada.getFecha(), r.getFecha());
    }

    /**
     * Test of deleteReserva method, of class ReservaLogic.
     */
    @Test
    public void testDeleteReserva() throws Exception {
        ReservaEntity entidad = reservaData.get(1);
        reservaLogic.deleteReserva(entidad.getId());
        reservaData.remove(entidad);
        ReservaEntity deleted = em.find(ReservaEntity.class, entidad.getId());
        Assert.assertNull(deleted);
    }

    
    
    
}
