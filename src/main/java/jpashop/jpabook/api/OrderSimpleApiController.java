package jpashop.jpabook.api;

import jpashop.jpabook.domain.Address;
import jpashop.jpabook.domain.Order;
import jpashop.jpabook.repository.OrderRepository;
import jpashop.jpabook.repository.OrderSearch;
import jpashop.jpabook.repository.order.simplequery.OrderSimpleQueryDto;
import jpashop.jpabook.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("api/v2/simple-orders")
    public List<SimpleOrderDto> findOrderv2(){
        List<Order> orderList =orderRepository.findAll(new OrderSearch());
        return orderList.stream().map(SimpleOrderDto::new).collect(Collectors.toList());
    }

    @GetMapping("api/v3/simple-orders")
    public List<SimpleOrderDto> findOrderv3(){
        List<Order> orderList =orderRepository.findAllWithMemberAndDelivery();
        return orderList.stream().map(SimpleOrderDto::new).collect(Collectors.toList());
    }

    @GetMapping("api/v4/simple-orders")
    public List<OrderSimpleQueryDto> findOrderv4(){

        return orderSimpleQueryRepository.findAllWithMemberAndAddress();
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private String orderStatus;
        private Address address;

        public SimpleOrderDto(Order order){
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus().toString();
            address = order.getDelivery().getAddress();
        }
    }


}
