package ca.mcgill.ecse321.artgallery.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
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


	 private Set<Address> addresses;

	@OneToMany(mappedBy="client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

}
