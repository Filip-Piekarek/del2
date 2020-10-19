package ca.mcgill.ecse321.artgallery.model;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

	@OneToMany(mappedBy="order", fetch=FetchType.EAGER, cascade= {CascadeType.ALL})
	public Set<Posting> getItems() {
		return this.items;
	}

	public void setItems(Set<Posting> itemss) {
		this.items = itemss;
	}
	
	public void addItem(Posting item) {
		this.items.add(item);
	}
	


	@Id
	@Column(unique=true)
	private long id;

	public void setId(long value) {
		this.id = value;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	
	@Enumerated
	private OrderStatus orderStatus;

	public void setOrderStatus(OrderStatus value) {
		this.orderStatus = value;
	}
	public OrderStatus getOrderStatus() {
		return this.orderStatus;
	}
}
