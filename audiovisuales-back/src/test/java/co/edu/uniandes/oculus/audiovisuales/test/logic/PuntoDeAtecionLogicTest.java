/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.test.logic;
import co.edu.uniandes.oculus.audiovisuales.entities.PuntoDeAtencionEntity;
import co.edu.uniandes.oculus.audiovisuales.persistence.PuntoDeAtencionPersistence;
import co.edu.uniandes.oculus.audiovisuales.ejbs.PuntoDeAtencionLogic;
import co.edu.uniandes.oculus.audiovisuales.api.IPuntoDeAtencionLogic;
import co.edu.uniandes.oculus.audiovisuales.ejbs.EquipoLogic;
import co.edu.uniandes.oculus.audiovisuales.entities.EquipoEntity;
import co.edu.uniandes.oculus.audiovisuales.api.IEquipoLogic;
import co.edu.uniandes.oculus.audiovisuales.exceptions.BusinessLogicException;
import co.edu.uniandes.oculus.audiovisuales.persistence.EquipoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 *
 * @author am.espinosa11
 */
@RunWith(Arquillian.class)
public class PuntoDeAtecionLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IPuntoDeAtencionLogic puntoDeAtencionLogic;
    @Inject
    private EquipoPersistence equipoPersistence;
    /**
     *
     */
    @PersistenceContext
    private EntityManager em;

    /**
     *
     */
    @Inject
    private UserTransaction utx;

    /**
     *
     */
    private List<PuntoDeAtencionEntity> puntoDeAtencionData = new ArrayList<PuntoDeAtencionEntity>();
    
   
    
    

     @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PuntoDeAtencionEntity.class.getPackage())
                .addPackage(PuntoDeAtencionLogic.class.getPackage())
                .addPackage(IPuntoDeAtencionLogic.class.getPackage())
                .addPackage(PuntoDeAtencionPersistence.class.getPackage())
                .addPackage(EquipoEntity.class.getPackage())
                .addPackage(EquipoLogic.class.getPackage())
                .addPackage(IEquipoLogic.class.getPackage())
                .addPackage(EquipoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
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
        em.createQuery("DELETE FROM EquipoEntity").executeUpdate();
        em.createQuery("DELETE FROM PuntoDeAtencionEntity").executeUpdate();
        em.createQuery("DELETE FROM AdministradorEntity").executeUpdate();
    }
     
    private void insertData()
    {
         for (int i = 0; i < 3; i++) {
            PuntoDeAtencionEntity entity = factory.manufacturePojo(PuntoDeAtencionEntity.class);
            for (EquipoEntity d : entity.getEquipos()) {
                d.setPuntoDeAtencion(entity);
            }
            em.persist(entity);
            puntoDeAtencionData.add(entity);
        }
    
    }
    
     /**
     * Test of getPuntosDeAtencion method, of class PuntoDeAtencionLogic.
     */
    @Test
    public void testGetPuntosDeAtencion() throws Exception 
    {
         List<PuntoDeAtencionEntity> listaP = puntoDeAtencionLogic.getPuntosDeAtencion();
         for (PuntoDeAtencionEntity entity : listaP) {
            boolean encontro = false;
            for (PuntoDeAtencionEntity storedEntity : puntoDeAtencionData) {
                if (entity.getId().equals(storedEntity.getId())) {
                    encontro = true;
                }
            }
            Assert.assertTrue(encontro);
        }
    }
    
     /**
     * Test of getPuntoDeAtencion method, of class PuntoDeAtencionLogic.
     */
    @Test
    public void testGetPuntoDeAtencion() throws Exception 
    {
       PuntoDeAtencionEntity punto1 = puntoDeAtencionData.get(0);
       PuntoDeAtencionEntity punto2 = puntoDeAtencionLogic.getPuntoDeAtencion(punto1.getId());
       Assert.assertNotNull(punto2);
       Assert.assertEquals(punto1.getId(), punto2.getId());
       //Assert.assertEquals(punto1.getUbicacion(), punto2.getUbicacion());
       
    }
    
     /**
     * Test of createPuntoDeAtencion method, of class PuntoDeAtencionLogic.
     */
    @Test
    public void testCreatePuntoDeAtencion() throws BusinessLogicException 
    {
         
        PuntoDeAtencionEntity newEntity = factory.manufacturePojo(PuntoDeAtencionEntity.class);
        for (EquipoEntity d : newEntity.getEquipos()) {
            d.setPuntoDeAtencion(newEntity);
        }

        PuntoDeAtencionEntity result = puntoDeAtencionLogic.createPuntoDeAtencion(newEntity);
        Assert.assertNotNull(result);

        PuntoDeAtencionEntity entity = em.find(PuntoDeAtencionEntity.class, result.getId());

        //Assert.assertEquals(newEntity.getUbicacion(), entity.getUbicacion());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertNotNull(entity.getEquipos());
        Assert.assertNotNull(result.getEquipos());
        Assert.assertEquals(result.getEquipos().size(), entity.getEquipos().size());

        for (EquipoEntity d : result.getEquipos()) {
            boolean found = false;
            for (EquipoEntity oracle : entity.getEquipos()) {
                if (d.getName().equals(oracle.getName())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);

        }
        
        
    }
    
    
    /**
     * Test of createPuntoDeAtencion con la regla method, of class PuntoDeAtencionLogic.
     */
    @Test(expected = BusinessLogicException.class)
    public void testCreatePuntoDeAtencionRegla() throws BusinessLogicException
    {
        PuntoDeAtencionEntity newEntity = factory.manufacturePojo(PuntoDeAtencionEntity.class);
        newEntity.setName(puntoDeAtencionData.get(0).getName());
        PuntoDeAtencionEntity result = puntoDeAtencionLogic.createPuntoDeAtencion(newEntity);
    }

    /**
     * Test of deletePuntoDeAtencion method, of class PuntoDeAtencionLogic.
     */
    @Test
    public void testDeletePuntoDeAtencion() throws Exception 
    {
        PuntoDeAtencionEntity punto = puntoDeAtencionData.get(1);
        puntoDeAtencionLogic.deletePuntoDeAtencion(punto.getId());
        PuntoDeAtencionEntity borrado = em.find(PuntoDeAtencionEntity.class, punto.getId());
        Assert.assertNull(borrado);
    }

    /**
     * Test of updatePuntoDeAtencion method, of class PuntoDeAtencionLogic.
     */
    @Test
    public void testUpdatePuntoDeAtencion() throws Exception 
    {
        PuntoDeAtencionEntity punto1 = puntoDeAtencionData.get(0);
        PuntoDeAtencionEntity pojoEntity = factory.manufacturePojo(PuntoDeAtencionEntity.class);

        pojoEntity.setId(punto1.getId());

        puntoDeAtencionLogic.updatePuntoDeAtencion(pojoEntity);

        PuntoDeAtencionEntity resp = em.find(PuntoDeAtencionEntity.class, punto1.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
      //  Assert.assertEquals(pojoEntity.getUbicacion(), resp.getUbicacion());
    }

    /**
     * Test of getByUbicacion method, of class PuntoDeAtencionLogic.
     */
   // @Test
    //public void testGetByUbicacion() throws Exception 
    //{
      // PuntoDeAtencionEntity punto1 = puntoDeAtencionData.get(0);
       //PuntoDeAtencionEntity punto2 = puntoDeAtencionLogic.getByUbicacion(punto1.getUbicacion());
       ///Assert.assertNotNull(punto2);
       //Assert.assertEquals(punto1.getId(), punto2.getId());
       
    //}
}
