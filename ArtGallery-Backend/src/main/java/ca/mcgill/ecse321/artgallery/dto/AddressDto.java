package ca.mcgill.ecse321.artgallery.dto;

public class AddressDto {

	private long id;
	private int streetNumber;
	private String streetName;
	private Integer appartmentNumber;
	private String city;
	private String province;
	private String country;
	private String postalCode;
	private ClientDto client;
	
	public AddressDto() {
	}
	
	public AddressDto(long id, int streetNumber, String streetName, Integer appartmentNumber, String city, String province, String country, String postalCode, ClientDto client) {
		this.id = id;
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.appartmentNumber = appartmentNumber;
		this.city = city;
		this.province = province;
		this.country = country;
		this.postalCode = postalCode;
		this.client = client;
	}
	
	public void setId(long value) {
		this.id = value;
	}

	public long getId() {
		return this.id;
	}
	
	public void setStreetNumber(int value) {
		this.streetNumber = value;
	}

	public int getStreetNumber() {
		return this.streetNumber;
	}
	
	public void setStreetName(String value) {
		this.streetName = value;
	}

	public String getStreetName() {
		return this.streetName;
	}
	
	public void setAppartmentNumber(Integer value) {
		this.appartmentNumber = value;
	}

	public Integer getAppartmentNumber() {
		return this.appartmentNumber;
	}
	
	public void setCity(String value) {
		this.city = value;
	}

	public String getCity() {
		return this.city;
	}
	
	public void setProvince(String value) {
		this.province = value;
	}

	public String getProvince() {
		return this.province;
	}
	
	public void setCountry(String value) {
		this.country = value;
	}

	public String getCountry() {
		return this.country;
	}
	
	public void setPostalCode(String value) {
		this.postalCode = value;
	}

	public String getPostalCode() {
		return this.postalCode;
	}
	
	public void setClient(ClientDto value) {
		this.client = value;
	}

	public ClientDto getClient() {
		return this.client;
	}
}
