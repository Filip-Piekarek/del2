package ca.mcgill.ecse321.artgallery.model;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order{

	@Column
	private Client client;

	@ManyToOne(optional=false)
	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	
	private Set<Posting> items;

	@OneToMany(mappedBy="order")
	public Set<Posting> getItems() {
		return this.items;
	}

	public void setItems(Set<Posting> itemss) {
		this.items = itemss;
	}

	@Id
	@Column(unique=true)
	private Integer id;

	public void setId(Integer value) {
		this.id = value;
	}
	
	@Id
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
	
	
	@Enumerated
	private OrderStatus orderStatus;

	public void setOrderStatus(OrderStatus value) {
		this.orderStatus = value;
	}
	public OrderStatus getOrderStatus() {
		return this.orderStatus;
	}
}
