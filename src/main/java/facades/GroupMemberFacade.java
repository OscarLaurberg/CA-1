package facades;

import entities.GroupMember;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class GroupMemberFacade {

    private static GroupMemberFacade instance;
    private static EntityManagerFactory emf;

    private GroupMemberFacade() {
    }

    public static GroupMemberFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GroupMemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public long getGroupMemberCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long groupMemberCount = (long) em.createQuery("SELECT COUNT(r) FROM GroupMember r").getSingleResult();
            return groupMemberCount;
        } finally {
            em.close();
        }
    }

    public GroupMember addGroupMember(String name, String color, String studentId) {
        GroupMember gm = new GroupMember(name, color,studentId);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(gm);
            em.getTransaction().commit();
            return gm;
        } finally {
            em.close();
        }

    }

    public void addGroupMembers(List<GroupMember> groupMembers) {
        for (GroupMember groupMember : groupMembers) {
            addGroupMember(groupMember.getName(), groupMember.getColor(), groupMember.getStudentId());
        }
    }

    public List<GroupMember> getAllGroupMembers() {
        EntityManager em = getEntityManager();
        TypedQuery q = em.createQuery("SELECT g FROM GroupMember g order by g.id asc", GroupMember.class);
        return q.getResultList();

    }

}
