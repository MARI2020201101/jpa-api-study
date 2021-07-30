package jpashop.jpabook.repository.order.simplequery;

import jpashop.jpabook.domain.Address;
import jpashop.jpabook.domain.Order;
import jpashop.jpabook.domain.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class OrderSimpleQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleQueryDto(Long id, String name, LocalDateTime orderDate, OrderStatus status, Address address){
        this.orderId = id;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = status;
        this.address = address;
    }
}
