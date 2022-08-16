package hellojpa;

import hellojpa.mapping.Member;
import hellojpa.mapping.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain_NPlus1 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setUsername("member1");

            em.persist(member);

            Team team = new Team();
            team.setName("teamA");
            member.setTeam(team);

            em.persist(team);
            em.flush();
            em.clear();

            //Member findMember = em.find(Member.class, member.getId());

            System.out.println("===============================");
            List<Member> members = em.createQuery("select m from Member m", Member.class)
                    .getResultList();
            System.out.println("===============================");

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

}
