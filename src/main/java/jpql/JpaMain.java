package jpql;

import javax.persistence.*;
import java.awt.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();//모든 데이터 변경은 transaction 에서 이루어져야 함

        tx.begin(); // 트랜잭션 시작

        try {
            Member member = new Member();
            member.setUserName("member1");
            member.setAge(15);
            em.persist(member);

            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);


            tx.commit(); //트랜잭션 커밋
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }



        emf.close();
    }
}
