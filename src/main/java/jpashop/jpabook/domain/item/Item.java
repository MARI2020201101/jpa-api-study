package jpashop.jpabook.domain.item;

import jpashop.jpabook.domain.Category;
import jpashop.jpabook.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public void addStock(int quantity){
        this.stockQuantity+= quantity;
    }

    public void removeStock(int quantity){
        if(quantity > this.stockQuantity){
            throw new NotEnoughStockException("Not Enough Stock Quantity");
        }
        this.stockQuantity -= quantity;
    }

}
