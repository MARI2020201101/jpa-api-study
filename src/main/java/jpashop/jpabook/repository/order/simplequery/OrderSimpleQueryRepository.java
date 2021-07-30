package jpashop.jpabook.repository.order.simplequery;

import jpashop.jpabook.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    public List<OrderSimpleQueryDto> findAllWithMemberAndAddress(){

        return em.createQuery("select new jpashop.jpabook.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " +
                "from Order o " +
                "join o.member m " +
                "join o.delivery d" , OrderSimpleQueryDto.class)
                .getResultList();
    }
}
