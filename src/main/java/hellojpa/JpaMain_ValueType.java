package hellojpa;

import hellojpa.valuetype.Address;
import hellojpa.valuetype.Member_collection;
import hellojpa.valuetype.Member_value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain_ValueType {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member_collection member = new Member_collection();
            member.setUsername("userA");
            member.setHomeAddress(new Address("city", "street", "12345"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new Address("city2", "street1", "10000"));
            member.getAddressHistory().add(new Address("city123", "street8", "12300"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("=======start============");
            Member_collection findMember = em.find(Member_collection.class, member.getId());
            System.out.println("==========getAddressHistory============");
            List<Address> addressHistory = findMember.getAddressHistory();
            for (Address address : addressHistory) {
                System.out.println("address = " + address.getCity());
            }

            // 수정
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            // 테이블 전체 delete하고 다시 insert 한다. -> 쿼리에 문제가 있다. 물론 원하는대로 동작은 한다.
            findMember.getAddressHistory().remove(new Address("city2", "street1", "10000"));
            findMember.getAddressHistory().add(new Address("newCity1", "street1", "10000"));

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
