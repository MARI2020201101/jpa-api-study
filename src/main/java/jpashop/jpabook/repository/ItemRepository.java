package jpashop.jpabook.repository;

import jpashop.jpabook.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public Item findOne(Long itemId){
        return em.find(Item.class, itemId);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }

    public void save(Item item){
        if(item.getId()==null){
            em.persist(item);
        }else{
            em.merge(item);
        }
    }
}
