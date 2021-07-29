package jpashop.jpabook;

import jpashop.jpabook.domain.*;
import jpashop.jpabook.domain.item.Book;
import jpashop.jpabook.service.MemberService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.makeSample1();
        initService.makeSample2();
    }

    @RequiredArgsConstructor
    @Component
    static class InitService{

        private final EntityManager em;

        @Transactional
        public void makeSample1(){
            Member member = new Member();
            member.setName("memberA");
            member.setAddress(new Address("서울","11","1111"));
            em.persist(member);

            Book book1 = new Book();
            book1.setName("JPA BOOK1");
            book1.setPrice(10000);
            book1.setStockQuantity(10);
            em.persist(book1);

            Book book2 = new Book();
            book2.setName("JPA BOOK2");
            book2.setPrice(20000);
            book2.setStockQuantity(20);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1,10000,1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2,40000,2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member,delivery,orderItem1, orderItem2);
            em.persist(order);
        }

        @Transactional
        public void makeSample2(){
            Member member = createMember("memberB","경기도","22","10101");
            em.persist(member);

            Book book1 = createBook("Spring Book 1",25000,100);
            em.persist(book1);

            Book book2 = createBook("Spring Book 2",35000,30);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1,25000,1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2,70000,2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member,delivery,orderItem1, orderItem2);
            em.persist(order);
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book book1 = new Book();
            book1.setName(name);
            book1.setPrice(price);
            book1.setStockQuantity(stockQuantity);
            return book1;
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city,street,zipcode));
            return member;
        }
    }

}
