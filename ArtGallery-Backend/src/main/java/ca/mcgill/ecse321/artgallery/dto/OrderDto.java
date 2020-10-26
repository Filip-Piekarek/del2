package ca.mcgill.ecse321.artgallery.dto;

import java.util.Collections;
import java.util.Set;

import ca.mcgill.ecse321.artgallery.model.OrderStatus;

public class OrderDto {

	private ClientDto client;
	private Set<PostingDto> items;
	private long id;
	private boolean inStorePickup;
	private OrderStatus orderStatus;
	
	public OrderDto() {
	}
	
	public OrderDto(ClientDto client, long id, boolean inStorePickup, OrderStatus orderStatus) {
		this(client, Collections.emptySet(), id, inStorePickup, orderStatus);
	}
	
	public OrderDto(ClientDto client, Set<PostingDto> items, long id, boolean inStorePickup, OrderStatus orderStatus) {
		this.client = client;
		this.items = items;
		this.id = id;
		this.inStorePickup = inStorePickup;
		this.orderStatus = orderStatus;
	}
	
	public ClientDto getClient() {
		return client;
	}
	
	public void setClient(ClientDto client) {
		this.client = client;
	}
	
	public Set<PostingDto> getItems() {
		return items;
	}
	
	public void setItems(Set<PostingDto> items) {
		this.items = items;
	}
	
	public PostingDto getItemById(long id){
		for (PostingDto post : this.items) {
			if (post.getId() == id) {
				return post;
			}
		}
		return null;
	}
	
	public void removeItemById(long id) {
		for (PostingDto item : this.items) {
			if (item.getId() == id) {
				this.items.remove(item);
			}
		}
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public boolean isInStorePickup() {
		return inStorePickup;
	}
	
	public void setInStorePickUp(boolean inStorePickup) {
		this.inStorePickup = inStorePickup;
	}
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
}
