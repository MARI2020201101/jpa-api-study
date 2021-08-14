package jpashop.jpabook.api;

import jpashop.jpabook.domain.Address;
import jpashop.jpabook.domain.Order;
import jpashop.jpabook.domain.OrderItem;
import jpashop.jpabook.domain.OrderStatus;
import jpashop.jpabook.repository.OrderRepository;
import jpashop.jpabook.repository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

    @GetMapping("api/v2/orders")
    public List<OrderDto> findOrderV2(){
        return orderRepository.findAll(new OrderSearch())
                .stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("api/v3/orders")
    public List<OrderDto> findOrderV3(){
        return orderRepository.findAllWithItems()
                .stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("api/v3-1/orders")
    public List<OrderDto> findOrderWithPageV3(
            @RequestParam(value = "offset", defaultValue = "0")int offset
            , @RequestParam(value = "limit", defaultValue = "100")int limit
                                              ){
        List<Order> orders = orderRepository.findAllWithPage(offset, limit);
        return orders.stream().map(OrderDto::new)
                .collect(Collectors.toList());
    }

    @Data
    static class OrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order){
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
            orderItems = order.getOrderitems()
                    .stream().map(OrderItemDto::new).collect(Collectors.toList());
        }
    }

    @Data
    static class OrderItemDto {
        private String name;
        private int orderPrice;
        private int count;

        public OrderItemDto(OrderItem orderItem){
            this.name = orderItem.getItem().getName();
            this.orderPrice = orderItem.getOrderPrice();
            this.count = orderItem.getCount();
        }
    }
}
