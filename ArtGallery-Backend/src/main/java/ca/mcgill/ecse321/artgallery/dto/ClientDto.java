package ca.mcgill.ecse321.artgallery.dto;

import java.util.Collections;
import java.util.Set;

public class ClientDto {

	private long id;
	private Set<OrderDto> orders;
	private String deliveryAddress;
	
	public ClientDto() {
	}
	
	public ClientDto(long id, String deliveryAddress) {
		this(id, Collections.emptySet(), deliveryAddress);
	}
	
	public ClientDto(long id, Set<OrderDto> orders, String deliveryAddress) {
		this.id = id;
		this.orders = orders;
		this.deliveryAddress = deliveryAddress;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Set<OrderDto> getOrders(){
		return orders;
	}
	
	public void setOrders(Set<OrderDto> orders) {
		this.orders = orders;
	}
	
	public OrderDto getOrderById(long id) {
		for (OrderDto i : this.orders) {
			if (i.getId() == id) {
				return i;
			}
		}
		return null;
	}
	
	public void removeOrderById(long id) {
		for (OrderDto order : this.orders) {
			if (order.getId()==id) {
				this.orders.remove(order);
			}
		}
	}
	
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
}
