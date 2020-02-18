package facades;


import utils.EMF_Creator;
import entities.GroupMember;
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
public class GroupMemberFacadeTest {

    private static EntityManagerFactory emf;
    private static GroupMemberFacade facade;

    public GroupMemberFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = GroupMemberFacade.getFacadeExample(emf);
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
        facade = GroupMemberFacade.getFacadeExample(emf);
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
            em.createNamedQuery("GroupMember.deleteAllRows").executeUpdate();
            em.getTransaction().commit();

            facade.addGroupMember("Oscar Laurberg", "Red","cph-ol38");
            facade.addGroupMember("Mads B", "Red","mussefar");
            facade.addGroupMember("Alexander Løve", "Red","Pølse");
            facade.addGroupMember("Benjamin", "Red","Codefather");


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
    public void testGetGroupMemberCount() {
        assertEquals(4, facade.getGroupMemberCount(), "Expects two rows in the database");
    }

    
    @Test
    public void testAddGroupMember() {
        int expected = facade.getAllGroupMembers().size()+1;
        facade.addGroupMember("This is a test", "test", "Unknown");
        int result = facade.getAllGroupMembers().size();
        assertEquals(expected,result);
               
    }
    
    @Test
    public void testGetAllJokes(){
        int expected = 4;
        int result = facade.getAllGroupMembers().size();
        assertEquals(expected,result);
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
