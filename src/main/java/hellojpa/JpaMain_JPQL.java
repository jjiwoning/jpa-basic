package hellojpa;

import hellojpa.jpql.Member_J;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain_JPQL {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member_J member = new Member_J();
            member.setUsername("userA");
            em.persist(member);

            em.flush();
            em.clear();

            List<Member_J> result = em.createQuery("select m from Member_J m", Member_J.class)
                    .getResultList();

            int n = 0;

            em.createQuery("select m from Member_J m order by m.age desc ", Member_J.class)
                    .setFirstResult(n)
                    .setMaxResults(n + 20)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
