import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Order{
   private Client buyer;
   
   @ManyToOne(optional=false)
   public Client getBuyer() {
      return this.buyer;
   }
   
   public void setBuyer(Client buyer) {
      this.buyer = buyer;
   }
   
   private Set<Posting> items;
   
   @OneToMany(mappedBy="order" )
   public Set<Posting> getItems() {
      return this.items;
   }
   
   public void setItems(Set<Posting> itemss) {
      this.items = itemss;
   }
   
   private Integer id;

public void setId(Integer value) {
    this.id = value;
}
public Integer getId() {
    return this.id;
}
private boolean inStorePickUp;

public void setInStorePickUp(boolean value) {
    this.inStorePickUp = value;
}
public boolean isInStorePickUp() {
    return this.inStorePickUp;
}
private OrderStatus orderStatus;

public void setOrderStatus(OrderStatus value) {
    this.orderStatus = value;
}
public OrderStatus getOrderStatus() {
    return this.orderStatus;
}
}
