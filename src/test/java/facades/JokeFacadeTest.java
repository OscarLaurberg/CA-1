package facades;

import dto.JokeDTO;
import utils.EMF_Creator;
import entities.Joke;
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

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class JokeFacadeTest {

    private static EntityManagerFactory emf;
    private static JokeFacade facade;

    public JokeFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = JokeFacade.getFacadeExample(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = JokeFacade.getFacadeExample(emf);
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
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.getTransaction().commit();

            facade.addJoke("Joke1", "Adult","Unknown", 18);
            facade.addJoke("Joke2", "Dad Joke","Unknown", 8);

        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testGetJokeCount() {
        assertEquals(2, facade.getJokeCount(), "Expects two rows in the database");
    }

    @Test
    public void testGetJokeById() {
        List<JokeDTO> allJokeDtos = facade.getAllJokes();
        JokeDTO expectedJokeDTO = allJokeDtos.get(0);
        Long id = expectedJokeDTO.getId();
        Joke resultJoke = facade.getJokeById(id.intValue());
        assertEquals(expectedJokeDTO.getPunchLine(),resultJoke.getPunchLine());
    }
    
    @Test
    public void testAddJoke() {
        int expected = facade.getAllJokes().size()+1;
        facade.addJoke("This is a test", "test", "Unknown",5);
        int result = facade.getAllJokes().size();
        assertEquals(expected,result);
               
    }
    
    @Test
    public void testGetAllJokes(){
        int expected = 2;
        int result = facade.getAllJokes().size();
        assertEquals(expected,result);
    }
    
        @Test
    public void testAddJokes(){
        Joke jk1 = new Joke ("Test1","Test1","Test1",2);
        Joke jk2 = new Joke ("Test1","Test1","Test1",2);
        List<Joke> jokes = new ArrayList();
        jokes.add(jk1);
        jokes.add(jk2);
        facade.addJokes(jokes);
        int expected = 4;
        int result = facade.getAllJokes().size();
        assertEquals(expected, result);
              
        
        
    }
    
    // contains bruger .equals metoden, så nedenstående virker ikke.
    // Går lige i tænkeboks mht. hvordan jeg tester getRandomJoke og arbejder videre med noget andet imens.
//    @Test
//    public void testGetRandomJoke(){
//        JokeDTO randomJokeDTO = facade.getRandomJoke();
//        List<JokeDTO> allJokes = facade.getAllJokes();
//        assertTrue(allJokes.contains(randomJokeDTO));
//    }

}
