package ca.mcgill.ecse321.artgallery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="addresses")
public class Address {

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
	private int streetNumber;

	public void setStreetNumber(int value) {
		this.streetNumber = value;
	}

	public int getStreetNumber() {
		return this.streetNumber;
	}

	@Column
	private String streetName;

	public void setStreetName(String value) {
		this.streetName = value;
	}

	public String getStreetName() {
		return this.streetName;
	}

	@Column
	private Integer appartmentNumber;

	public void setAppartmentNumber(Integer value) {
		this.appartmentNumber = value;
	}

	public Integer getAppartmentNumber() {
		return this.appartmentNumber;
	}

	@Column
	private String city;

	public void setCity(String value) {
		this.city = value;
	}

	public String getCity() {
		return this.city;
	}

	@Column
	private String province;

	public void setProvince(String value) {
		this.province = value;
	}

	public String getProvince() {
		return this.province;
	}

	@Column
	private String country;

	public void setCountry(String value) {
		this.country = value;
	}

	public String getCountry() {
		return this.country;
	}

	@Column
	private String postalCode;

	public void setPostalCode(String value) {
		this.postalCode = value;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	@Column
	private Client client;

	
	public void setClient(Client value) {
		this.client = value;
	}

	@ManyToOne(optional=false)
	public Client getClient() {
		return this.client;
	}
}
