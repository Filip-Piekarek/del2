package ca.mcgill.ecse321.artgallery.dto;

public class PostingDto {

	private OrderDto order;
	private ArtworkDto item;
	private double price;
	private long id;
	private boolean visibility;
	
	public PostingDto() {
	}
	
	public PostingDto(OrderDto order, ArtworkDto item, double price, long id, boolean visibility) {
		this.order = order;
		this.item = item;
		this.price = price;
		this.id = id;
		this.visibility = visibility;
	}
	
	public OrderDto getOrder() {
		return order;
	}
	
	public void setOrder(OrderDto order) {
		this.order = order;
	}
	
	public ArtworkDto GetItem() {
		return item;
	}
	
	public void setItem(ArtworkDto item) {
		this.item = item;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public boolean isVisibility() {
		return visibility;
	}
	
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
}
