/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.test.logic;

import co.edu.uniandes.oculus.audiovisuales.api.IAdministradorLogic;
import co.edu.uniandes.oculus.audiovisuales.ejbs.AdministradorLogic;
import co.edu.uniandes.oculus.audiovisuales.entities.AdministradorEntity;
import co.edu.uniandes.oculus.audiovisuales.exceptions.BusinessLogicException;
import co.edu.uniandes.oculus.audiovisuales.persistence.AdministradorPersistence;
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
 *Clase que representa las pruebas de la parte logica de administrador
 * @author Sneider Velandia G
 */
@RunWith(Arquillian.class)
public class AdministradorLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IAdministradorLogic administradorLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<AdministradorEntity> data = new ArrayList<AdministradorEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdministradorEntity.class.getPackage())
                .addPackage(AdministradorLogic.class.getPackage())
                .addPackage(IAdministradorLogic.class.getPackage())
                .addPackage(AdministradorPersistence.class.getPackage())
                .addPackage(BusinessLogicException.class.getPackage())
                
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUp() 
    {
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
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            AdministradorEntity entity = factory.manufacturePojo(AdministradorEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    //falta pruebas del CREATE
    
    
    /**
     * Prueba para consultar la lista de Administradores
     */
    @Test
    public void getAdministradoresTest() 
    {
        List<AdministradorEntity> list = administradorLogic.getAdministradores();
        Assert.assertEquals(data.size(), list.size());
        for (AdministradorEntity adminEntity : list) {
            boolean found = false;
            for (AdministradorEntity almaceEntity : data) {
                if (adminEntity.getId().equals(almaceEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Administrador por ID
     */
    @Test
    public void getAdministradorTest() 
    {
        AdministradorEntity adminEntity = data.get(0);
        AdministradorEntity resultAdminEntity = administradorLogic.getAdministrador(adminEntity.getId());
        Assert.assertNotNull(resultAdminEntity);
        Assert.assertEquals(adminEntity.getName(), resultAdminEntity.getName());
        Assert.assertEquals(adminEntity.getId(), resultAdminEntity.getId());
    }
    
     /**
     * Prueba para eliminar un Administrador
     */
    @Test
    public void deleteAdministradorTest() 
    {
        AdministradorEntity adminEntity = data.get(1);
        administradorLogic.deleteAdministrador(adminEntity.getId());
        AdministradorEntity adminEliminar = em.find(AdministradorEntity.class, adminEntity.getId());
        Assert.assertNull(adminEliminar);
    }
    
     /**
     * Prueba para actualizar un Administrador
     */
    @Test
    public void updateCompanyTest() {
        AdministradorEntity adminEntity = data.get(0);
        AdministradorEntity pojoEntityAdmin = factory.manufacturePojo(AdministradorEntity.class);

        pojoEntityAdmin.setId(adminEntity.getId());

        administradorLogic.updateAdministrador(pojoEntityAdmin);

        AdministradorEntity respAdmin = em.find(AdministradorEntity.class, adminEntity.getId());

        Assert.assertEquals(pojoEntityAdmin.getName(), respAdmin.getName());
        Assert.assertEquals(pojoEntityAdmin.getId(), respAdmin.getId());
        Assert.assertEquals(pojoEntityAdmin.getPuntoDeAtencion(),respAdmin.getPuntoDeAtencion());
    }
}

