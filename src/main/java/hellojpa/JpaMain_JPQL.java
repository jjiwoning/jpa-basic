package hellojpa;

import hellojpa.jpql.Member_J;
import hellojpa.jpql.Team_J;

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
            Team_J teamA = new Team_J();
            teamA.setName("teamA");
            em.persist(teamA);

            Team_J teamB = new Team_J();
            teamB.setName("teamB");
            em.persist(teamB);

            Member_J member1 = new Member_J();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member_J member2 = new Member_J();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member_J member3 = new Member_J();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

            String query = "select m from Member_J m join fetch m.team";

            List<Member_J> members = em.createQuery(query, Member_J.class)
                    .getResultList();

            for (Member_J member : members) {
                System.out.println(member.getUsername() + " " + member.getTeam().getName());
            }

            System.out.println("==================================");

            String query2 = "select distinct t from Team_J t join fetch t.members where t.name = 'teamA'";
            List<Team_J> teams = em.createQuery(query2, Team_J.class)
                    .getResultList();

            for (Team_J team : teams) {
                System.out.println(team.getMembers().size());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
