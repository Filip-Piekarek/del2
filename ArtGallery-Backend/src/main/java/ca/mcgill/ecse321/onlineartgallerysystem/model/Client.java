package ca.mcgill.ecse321.onlineartgallerysystem.model;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Client extends UserRole{
   private Set<Order> orders;
   
   @OneToMany(mappedBy="client" )
   public Set<Order> getOrders() {
      return this.orders;
   }
   
   public void setOrders(Set<Order> orderss) {
      this.orders = orderss;
   }
   
   private String deliveryAddress;

public void setDeliveryAddress(String value) {
    this.deliveryAddress = value;
}
public String getDeliveryAddress() {
    return this.deliveryAddress;
}
}
