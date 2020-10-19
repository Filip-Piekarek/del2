package ca.mcgill.ecse321.artgallery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;



@Entity
public class Profile{

	@Id
	@Column(unique=true)
	private String username;

	public void setUsername(String value) {
		this.username = value;
	}
	@Id
	public String getUsername() {
		return this.username;
	}
	
	private boolean isArtistProfile;

	public void setIsArtistProfile(boolean value) {
		this.isArtistProfile = value;
	}
	
	public boolean isIsArtistProfile() {
		return this.isArtistProfile;
	}
	@Column
	private String phoneNumber;

	public void setPhoneNumber(String value) {
		this.phoneNumber = value;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	@Column
	private String emailAddress;

	public void setEmailAddress(String value) {
		this.emailAddress = value;
	}
	
	public String getEmailAddress() {
		return this.emailAddress;
	}
	
	@Column
	private User user;

	@OneToOne(optional=false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Column
	public String password;
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return this.password;
	}
}
