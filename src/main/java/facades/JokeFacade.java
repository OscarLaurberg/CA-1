package facades;

import dto.JokeDTO;
import entities.Joke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class JokeFacade {

    private static JokeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private JokeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getJokeCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long jokeCount = (long) em.createQuery("SELECT COUNT(r) FROM Joke r").getSingleResult();
            return jokeCount;
        } finally {
            em.close();
        }
    }

    public Joke getJokeById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Joke joke = em.find(Joke.class, (long) id);
            return joke;
        } finally {
            em.close();
        }
    }

    public Joke addJoke(String punchLine, String category, String reference, int ageRestriction) {
        Joke joke = new Joke(punchLine, category,reference, ageRestriction);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(joke);
            em.getTransaction().commit();
            return joke;
        } finally {
            em.close();
        }
    }

    public void addJokes(List<Joke> jokes) {
        for (Joke joke : jokes) {
            addJoke(joke.getPunchLine(), joke.getCategory(), joke.getCategory(), joke.getAgeRestriction());
        }
    }
    public List<JokeDTO> getAllJokes() {
        EntityManager em = getEntityManager();
        TypedQuery q = em.createQuery("SELECT j FROM Joke j order by j.id asc", Joke.class);
        List<Joke> allJokes = q.getResultList();
        List<JokeDTO> allJokesAsDTO = new ArrayList();
        for (Joke joke : allJokes) {
            JokeDTO jokeDTO = new JokeDTO(joke);
            allJokesAsDTO.add(jokeDTO);
        }
        return allJokesAsDTO;
    }
    
    public JokeDTO getRandomJoke(){
        List <JokeDTO> allJokes = getAllJokes();
        int amountOfJokes = allJokes.size();
        Random rand = new Random();
        int randomIndex = rand.nextInt(amountOfJokes-1);
        return allJokes.get(randomIndex);
        
    }
    
}
