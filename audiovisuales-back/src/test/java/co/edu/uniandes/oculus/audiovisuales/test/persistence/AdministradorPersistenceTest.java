/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.test.persistence;

import co.edu.uniandes.oculus.audiovisuales.entities.AdministradorEntity;
import co.edu.uniandes.oculus.audiovisuales.persistence.AdministradorPersistence;
import co.edu.uniandes.oculus.audiovisuales.persistence.PuntoDeAtencionPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Sneider Velandia G
 */
@RunWith(Arquillian.class)
public class AdministradorPersistenceTest
{
    /**
     *
     * @return el jar que va a desplegar para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdministradorEntity.class.getPackage())
                .addPackage(AdministradorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
    // genera la conexion con mi clase persistence, evita que hagamos el new
    @Inject
    private AdministradorPersistence administradorPersistence;
    private PuntoDeAtencionPersistence puntoDeAtencionPersistence;
    
    // es el oraculo... va y mira a la base de datos
    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    //contiene los datos que va a tener la base de datos
    private List<AdministradorEntity> data = new ArrayList<AdministradorEntity>();
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
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
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     * se borra primero los hijos y despues su padre 
     */
    private void clearData() {
        em.createQuery("DELETE FROM PuntoDeAtencionEntity").executeUpdate();
        em.createQuery("DELETE FROM AdministradorEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *                 ¿ como creo hijos ? no es necesario
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            AdministradorEntity entity = factory.manufacturePojo(AdministradorEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /// pruebas sobre el CRUD \\\
    
    /**
     * Prueba para crear un Administrador.
     */
    @Test
    public void createAdministradorTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        
        AdministradorEntity result = administradorPersistence.create(newEntity);
                
        Assert.assertNotNull(result);
        AdministradorEntity entity = em.find(AdministradorEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(),entity.getId());
    }
    
     /**
     * Prueba para eliminar un Administrador.
     */
    @Test
    public void deleteAdministradorTest() {
        AdministradorEntity entity = data.get(0);
        administradorPersistence.delete(entity.getId());
        AdministradorEntity deleted = em.find(AdministradorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un Administrador.
     */
    @Test
    public void updateAdministradorTest() {
        AdministradorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);

        newEntity.setId(entity.getId());

        administradorPersistence.update(newEntity);

        AdministradorEntity resp = em.find(AdministradorEntity.class, entity.getId());
        
        //comparamos que el que esta en la base de datos es igual al que esta generado por el PODAM
        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    /**
     * Prueba para consultar la lista de Adminsitradores.
     */
    @Test
    public void getAdministradoresTest() {
        List<AdministradorEntity> list = administradorPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (AdministradorEntity ent : list) 
        {
            boolean found = false;
            for (AdministradorEntity entity : data) 
            {
                if (ent.getId().equals(entity.getId())) 
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Administrador.
     */
    @Test
    public void getAdministradorTest() {
        AdministradorEntity entity = data.get(0);
        AdministradorEntity newEntity = administradorPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    /**
     * Prueba para consultar un Administrador 
     */
    @Test
    public void getAdministradorByNameTest()
    {
        AdministradorEntity adminEnti  = data.get(0);
        AdministradorEntity nuevaEntidadAdmin = administradorPersistence.findByName(adminEnti.getName());
        Assert.assertNotNull(nuevaEntidadAdmin);
        Assert.assertEquals(adminEnti.getPuntoDeAtencion(),nuevaEntidadAdmin.getPuntoDeAtencion());
        Assert.assertEquals(adminEnti.getName(), nuevaEntidadAdmin.getName());
        Assert.assertEquals(adminEnti.getId(), nuevaEntidadAdmin.getId());
    }
    
    /**
     * Prueba para consultar un Administrador por un punto de atencion 
     */
    public void getAdministradorByPuntoDeAtencion()
    {
        //????????
        
    }
    
}
