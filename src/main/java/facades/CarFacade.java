/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CarDTO;
import entities.Car;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author oscar
 */
public class CarFacade {

    private static CarFacade instance;
    private static EntityManagerFactory emf;

    private CarFacade() {

    }

    public static CarFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public long getCarCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long carCount = (long) em.createQuery("SELECT COUNT(r) FROM Car r").getSingleResult();
            return carCount;
        } finally {
            em.close();
        }
    }

    public List<CarDTO> getAllCars() {
        EntityManager em = getEntityManager();
        TypedQuery<Car> q = em.createQuery("SELECT c FROM Car c", Car.class);
        List<Car> allCars = q.getResultList();
        List<CarDTO> allCarsAsDTOs = new ArrayList();
        for (Car car : allCars) {
            CarDTO carDTO = new CarDTO(car);
            allCarsAsDTOs.add(carDTO);
        }
        return allCarsAsDTOs;
    }

    public List<CarDTO> getCarsByMake(String make) {
        EntityManager em = getEntityManager();
        TypedQuery<Car> q = em.createQuery("SELECT c FROM Car c WHERE c.make LIKE CONCAT('%',:make,'%')", Car.class);
        q.setParameter("make", make);
        List<Car> cars = q.getResultList();
        List<CarDTO> carDTOs = new ArrayList();
        for (Car car : cars) {
            CarDTO carDTO = new CarDTO(car);
            carDTOs.add(carDTO);
        }
        return carDTOs;
    }

    public List<CarDTO> getCarsByModel(String model) {
        EntityManager em = getEntityManager();
        TypedQuery<Car> q = em.createQuery("SELECT c FROM Car c WHERE c.model LIKE CONCAT('%',:model,'%')", Car.class);
        q.setParameter("model", model);
        List<Car> cars = q.getResultList();
        List<CarDTO> carDTOs = new ArrayList();
        for (Car car : cars) {
            CarDTO carDTO = new CarDTO(car);
            carDTOs.add(carDTO);
        }
        return carDTOs;
    }

    public List<CarDTO> getCarsByYear(int year) {
        EntityManager em = getEntityManager();
        TypedQuery<Car> q = em.createQuery("SELECT c FROM Car c WHERE c.year = :year", Car.class);
        q.setParameter("year", year);
        List<Car> cars = q.getResultList();
        List<CarDTO> carDTOs = new ArrayList();
        for (Car car : cars) {
            CarDTO carDTO = new CarDTO(car);
            carDTOs.add(carDTO);
        }
        return carDTOs;
    }

    public List<CarDTO> getCarsById(int id) {
        EntityManager em = getEntityManager();
        List<Car> cars = new ArrayList();
        Car car = em.find(Car.class, (long) id);
        return Arrays.asList(new CarDTO(car));
    }
    
    //public Car(int year, String make, String model, double price, String owner)
    
    public Car addCar(Car car){
        Car carToBeAdded = new Car(car.getYear(),car.getMake(),car.getModel(),car.getPrice(),car.getOwner());
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(carToBeAdded);
            em.getTransaction().commit();
            return carToBeAdded;
        }finally{
            em.close();
        }
    }
    
    public List<CarDTO> addCars (List<Car> cars){
        List<CarDTO> carDTOs = new ArrayList();
        for (Car car : cars){
            addCar(car);
            carDTOs.add(new CarDTO(car));
        }
        return carDTOs;
    }
}
