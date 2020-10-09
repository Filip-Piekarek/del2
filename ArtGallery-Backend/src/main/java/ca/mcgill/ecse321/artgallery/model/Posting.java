package ca.mcgill.ecse321.artgallery.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

	private OnlineArtGallerySystem system;

	public void setSystem(OnlineArtGallerySystem value) {
		this.system = value;
	}
	public OnlineArtGallerySystem getSystem() {
		return this.system;
	}
	private Artwork item;

	@OneToOne(optional=false)
	public Artwork getItem() {
		return this.item;
	}

	public void setItem(Artwork item) {
		this.item = item;
	}
	@Id
	private long id;

	public void setId(long value) {
		this.id = value;
	}
	@Id
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
	private Inventory inventory;

	@ManyToOne(optional=false)
	public Inventory getInventory() {
		return this.inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}
