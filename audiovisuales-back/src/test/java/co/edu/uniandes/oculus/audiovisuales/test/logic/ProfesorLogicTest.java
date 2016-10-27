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
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fa.lopez10
 */
public class ProfesorLogicTest {
    
    public ProfesorLogicTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
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
    }

    /**
     * Test of getProfesor method, of class ProfesorLogic.
     */
    @Test
    public void testGetProfesor() throws Exception {
    }

    /**
     * Test of getProfesorByName method, of class ProfesorLogic.
     */
    @Test
    public void testGetProfesorByName() throws Exception {
    }

    /**
     * Test of createProfesor method, of class ProfesorLogic.
     */
    @Test
    public void testCreateProfesor() throws Exception {
    }

    /**
     * Test of updateProfesor method, of class ProfesorLogic.
     */
    @Test
    public void testUpdateProfesor() throws Exception {
    }

    /**
     * Test of deleteProfesor method, of class ProfesorLogic.
     */
    @Test
    public void testDeleteProfesor() throws Exception {
    }

    /**
     * Test of listReservasRangoFechas method, of class ProfesorLogic.
     */
    @Test
    public void testListReservasRangoFechas() throws Exception {
    }
    
}
