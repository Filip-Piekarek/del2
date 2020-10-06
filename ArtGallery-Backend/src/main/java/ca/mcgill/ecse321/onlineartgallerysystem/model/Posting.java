import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import ca.mcgill.ecse321.onlineartgallerysystem.model.OnlineArtGallerySystem;
import javax.persistence.OneToOne;

@Entity
public class Posting{
   private Order order;
   
   @ManyToOne(optional=false)
   public Order getOrder() {
      return this.order;
   }
   
   public void setOrder(Order order) {
      this.order = order;
   }
   
   private Inventory ;
   
   @ManyToOne
   public Inventory get() {
      return this.;
   }
   
   public void set(Inventory ) {
      this. = ;
   }
   
   private OnlineArtGallerySystem system;
   
   @ManyToOne(optional=false)
   public OnlineArtGallerySystem getSystem() {
      return this.system;
   }
   
   public void setSystem(OnlineArtGallerySystem system) {
      this.system = system;
   }
   
   private Artwork item;
   
   @OneToOne(optional=false)
   public Artwork getItem() {
      return this.item;
   }
   
   public void setItem(Artwork item) {
      this.item = item;
   }
   
   private long id;

public void setId(long value) {
    this.id = value;
}
public long getId() {
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
private boolean visibility;

public void setVisibility(boolean value) {
    this.visibility = value;
}
public boolean isVisibility() {
    return this.visibility;
}
private String/*No type specified!*/ var2;

public void setVar2(String/*No type specified!*/ value) {
    this.var2 = value;
}
public String/*No type specified!*/ getVar2() {
    return this.var2;
}
}
