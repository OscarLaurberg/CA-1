/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dto.CarDTO;
import entities.Car;
import entities.Joke;
import facades.CarFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author oscar
 */
@Path("cars")
public class CarResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/startcode",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final CarFacade FACADE = CarFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CarResource
     */
    public CarResource() {
    }

    /**
     * Retrieves representation of an instance of rest.CarResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCars() {
        
        List<CarDTO> allCars = FACADE.getAllCars();
        return Response.ok(allCars).build();
        //TODO return proper representation object
    }

    @POST
    @Path("add")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addCars(String json) {
        List<Car> cars = GSON.fromJson(json, new TypeToken<List<Car>>() {
        }.getType());
        List<CarDTO> carDTOs = FACADE.addCars(cars);
        return Response.ok(carDTOs).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCArFromId(@PathParam("id") int id) {
        List<CarDTO> car = FACADE.getCarsById(id);
        return GSON.toJson(car);
    }
    
    /**
     * PUT method for updating or creating an instance of CarResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

}
