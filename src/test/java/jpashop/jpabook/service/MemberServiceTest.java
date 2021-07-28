package jpashop.jpabook.service;

import jpashop.jpabook.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    EntityManager em;

    @Test
    public void memberJoinTest(){
        Member member = new Member();
        member.setName("Kim");
        memberService.join(member);
    }

    @Test(expected = IllegalStateException.class)
    public void duplicateMemberTest(){
        Member member1 = new Member();
        member1.setName("Lee");
        Member member2 = new Member();
        member2.setName("Lee");

        memberService.join(member1);
        memberService.join(member2);
        em.flush();
    }

}
