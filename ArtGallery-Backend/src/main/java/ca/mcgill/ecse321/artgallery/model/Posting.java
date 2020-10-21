package ca.mcgill.ecse321.artgallery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="postings")
public class Posting{

	@Column
	private Order order;

	@ManyToOne(optional=true)
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Column
	private Artwork item;

	@OneToOne(optional=false)
	public Artwork getItem() {
		return this.item;
	}

	public void setItem(Artwork item) {
		this.item = item;
	}

	@Column
	private double price;

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	@Column
	private boolean visibility;

	public void setVisibility(boolean value) {
		this.visibility = value;
	}
	public boolean isVisibility() {
		return this.visibility;
	}
	
	@Column
	private boolean isOnline;

	public void setIsOnline(boolean value) {
		this.isOnline = value;
	}
	public boolean isIsOnline() {
		return this.isOnline;
	}
	
	@Column
	private int priority;

	public void setPriority(int value) {
		this.priority = value;
	}
	public int getPriority() {
		return this.priority;
	}

}
