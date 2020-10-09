package ca.mcgill.ecse321.artgallery.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="clients")
public class Client extends UserRole{

	@Id
	@Column(unique=true)
	private long id;

	public void setId(long value) {
		this.id = value;
	}
	@Id
	public long getId() {
		return this.id;
	}

	
	
	private Set<Order> orders;

	@OneToMany(mappedBy="client")
	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orderss) {
		this.orders = orderss;
	}

	@Column
	private String deliveryAddress;

	public void setDeliveryAddress(String value) {
		this.deliveryAddress = value;
	}
	
	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}
}
