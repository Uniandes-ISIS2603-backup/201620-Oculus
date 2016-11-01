/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.oculus.audiovisuales.test.logic;

import co.edu.uniandes.oculus.audiovisuales.api.IProfesorLogic;
import co.edu.uniandes.oculus.audiovisuales.api.IReservaLogic;
import co.edu.uniandes.oculus.audiovisuales.ejbs.ProfesorLogic;
import co.edu.uniandes.oculus.audiovisuales.ejbs.ReservaLogic;
import co.edu.uniandes.oculus.audiovisuales.entities.ProfesorEntity;
import co.edu.uniandes.oculus.audiovisuales.entities.ReservaEntity;
import co.edu.uniandes.oculus.audiovisuales.persistence.ProfesorPersistence;
import co.edu.uniandes.oculus.audiovisuales.persistence.ReservaPersistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author fa.lopez10
 */
@RunWith(Arquillian.class)
public class ProfesorLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IProfesorLogic profesorLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<ProfesorEntity> profesorData = new ArrayList<ProfesorEntity>();
    
    private List<ArrayList<ReservaEntity>> reservasData = new  ArrayList<ArrayList<ReservaEntity>>();
    
    public ProfesorLogicTest() {
    }
    
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
    }
    
    private void insertData()
    {
        System.out.print("entra a insrtData");
        for (int i = 0; i < 3; i++) {
            ProfesorEntity entity = factory.manufacturePojo(ProfesorEntity.class);
            for (ReservaEntity r : entity.getReservas()) {
                r.setProfesor(entity);
            }
            em.persist(entity);
            profesorData.add(entity);
            System.out.print("size es" + profesorData.size());
        }
        
        
    }
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProfesorEntity.class.getPackage())
                .addPackage(ProfesorLogic.class.getPackage())
                .addPackage(IProfesorLogic.class.getPackage())
                .addPackage(ProfesorPersistence.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaLogic.class.getPackage())
                .addPackage(IReservaLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Test of getProfesores method, of class ProfesorLogic.
     */
    @Test
    public void testGetProfesores() throws Exception {
        List<ProfesorEntity> listaP = profesorLogic.getProfesores();
        for (ProfesorEntity entity : listaP) {
            boolean encontro = false;
            for (ProfesorEntity storedEntity : profesorData) {
                if (entity.getId().equals(storedEntity.getId())) {
                    encontro = true;
                }
            }
            Assert.assertTrue(encontro);
        }
    }
    
    /**
     * Test of getProfesor method, of class ProfesorLogic.
     */
    @Test
    public void testGetProfesor() throws Exception {
        ProfesorEntity profe1 = profesorData.get(0);
        ProfesorEntity profe2 = profesorLogic.getProfesor(profe1.getId());
        Assert.assertNotNull(profe2);
        Assert.assertEquals(profe1.getId(), profe2.getId());
        Assert.assertEquals(profe1.getCodigo(), profe2.getCodigo());
        Assert.assertEquals(profe1.getLogin(), profe2.getLogin());
        Assert.assertEquals(profe1.getName(), profe2.getName());
    }
    
    
    /**
     * Test of createProfesor method, of class ProfesorLogic.
     */
    @Test
    public void testCreateProfesor() throws Exception {
        ProfesorEntity newEntity = factory.manufacturePojo(ProfesorEntity.class);
        for (ReservaEntity d : newEntity.getReservas()) {
            d.setProfesor(newEntity);
        }
        
        ProfesorEntity result = profesorLogic.createProfesor(newEntity);
        Assert.assertNotNull(result);
        
        ProfesorEntity entity = em.find(ProfesorEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getCodigo(), entity.getCodigo());
        Assert.assertEquals(newEntity.getLogin(), entity.getLogin());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertNotNull(entity.getReservas());
        Assert.assertNotNull(result.getReservas());
        Assert.assertEquals(result.getReservas().size(), entity.getReservas().size());
        
        for (ReservaEntity d : result.getReservas()) {
            boolean found = false;
            for (ReservaEntity reserva : entity.getReservas()) {
                if (d.getId().equals(reserva.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
            
        }
    }
    
    /**
     * Test of updateProfesor method, of class ProfesorLogic.
     */
    @Test
    public void testUpdateProfesor() throws Exception {
        ProfesorEntity profe1 = profesorData.get(0);
        ProfesorEntity pojoEntity = factory.manufacturePojo(ProfesorEntity.class);
        
        pojoEntity.setId(profe1.getId());
        
        profesorLogic.updateProfesor(pojoEntity);
        
        ProfesorEntity resp = em.find(ProfesorEntity.class, profe1.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getLogin(), resp.getLogin());
        Assert.assertEquals(pojoEntity.getCodigo(), resp.getCodigo());
    }
    
    /**
     * Test of deleteProfesor method, of class ProfesorLogic.
     */
    @Test
    public void testDeleteProfesor() throws Exception {
        ProfesorEntity profe = profesorData.get(1);
        profesorLogic.deleteProfesor(profe.getId());
        profesorData.remove(profe);
        ProfesorEntity borrado = em.find(ProfesorEntity.class, profe.getId());
        Assert.assertNull(borrado);
    }
    
    /**
     * Test of listReservasRangoFechas method, of class ProfesorLogic.
     */
    @Test
    public void testListReservasRangoFechas() throws Exception {
        ProfesorEntity result = profesorLogic.createProfesor(factory.manufacturePojo(ProfesorEntity.class));
        
        ProfesorEntity entity = em.find(ProfesorEntity.class, result.getId());
        boolean found = false;
        Date fecha1 = new Date(2000,12,12);
        Date fecha2 = new Date();
       
        //La idea es calcular la lista con el resulty mirando una por una de las que
        //se incluyen si son iguales a las obtenidas por el metodo
        
        Assert.assertTrue(found);
        
        
    }
    
}
