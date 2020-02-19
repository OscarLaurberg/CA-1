/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import utils.EMF_Creator;
import entities.Car;
import dto.CarDTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.validation.constraints.AssertTrue;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

/**
 *
 * @author oscar
 */
public class CarFacadeTest {

    private static EntityManagerFactory emf;
    private static CarFacade facade;

    public CarFacadeTest() {

    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = CarFacade.getFacadeExample(emf);
    }

    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = CarFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
            Car car1 = new Car(1993, "Skoda", "Felicia", 5000, "Chefen");
            Car car2 = new Car(2000, "Diller", "Penis", 1000, "Ulrich");

            facade.addCar(car1);
            facade.addCar(car2);

        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testGetCarCount() {
        assertEquals(2, facade.getCarCount());
    }

    @Test
    public void testGetAllCars() {
        int expected = 2;
        int result = facade.getAllCars().size();
        assertEquals(expected, result);
    }

    @Test
    public void testGetCarsByMake() {
        int expectedSize = 1;
        List<CarDTO> resultList = facade.getCarsByMake("Skoda");
        int resultSize = resultList.size();
        assertEquals(expectedSize, resultSize);
        String expectedMake = "Skoda";
        assertEquals(expectedMake,resultList.get(0).getMake());
    }
    
    @Test
    public void testGetCarsByModel(){
        String expected = "Felicia";
        String result = facade.getCarsByModel("Felicia").get(0).getModel();
        assertEquals(expected,result);
    }
    
    @Test
    public void testGetCarsByYear(){
        int expected = 1993;
        int result = facade.getCarsByYear(1993).get(0).getYear();
        assertEquals(expected, result);
    }
    
    @Test
    public void testAddCar(){
        Car car3 = new Car(1815,"test","test",500,"test");
        int expected = facade.getAllCars().size()+1;
        facade.addCar(car3);
        int result = facade.getAllCars().size();
    }
}
