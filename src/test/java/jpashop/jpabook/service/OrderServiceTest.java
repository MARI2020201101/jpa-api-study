package jpashop.jpabook.service;

import jpashop.jpabook.domain.Address;
import jpashop.jpabook.domain.Member;
import jpashop.jpabook.domain.Order;
import jpashop.jpabook.domain.OrderStatus;
import jpashop.jpabook.domain.item.Book;
import jpashop.jpabook.domain.item.Item;
import jpashop.jpabook.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired OrderService orderService;
    @Autowired EntityManager em;
    @Autowired OrderRepository orderRepository;

    @Test
    public void orderTest(){
        Member member = new Member();
        member.setName("John");
        member.setAddress(new Address("NewYork","BakerStreet","123-456"));
        em.persist(member);

        Item book = new Book();
        book.setName("JPA Tutorial");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(),book.getId(), orderCount);
        Order findOrder = orderRepository.findOne(orderId);
        assertEquals("Order Status Test", OrderStatus.ORDER,findOrder.getStatus());
        assertEquals("Item Stock Check",8,book.getStockQuantity());
    }
    @Test
    public void cancelTest(){
        Member member = new Member();
        member.setName("Homes");
        member.setAddress(new Address("NewYork","BakerStreet","123-456"));
        em.persist(member);

        Item book = new Book();
        book.setName("JPA Tutorial2");
        book.setPrice(15000);
        book.setStockQuantity(20);
        em.persist(book);

        int orderCount = 1;

        Long orderId = orderService.order(member.getId(),book.getId(), orderCount);
        orderService.cancel(orderId);
        assertEquals("Stock Quantity Check",20,book.getStockQuantity());

    }
}
