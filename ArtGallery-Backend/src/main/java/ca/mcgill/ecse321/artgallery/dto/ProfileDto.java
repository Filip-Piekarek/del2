package ca.mcgill.ecse321.artgallery.dto;

public class ProfileDto {

	private String username;
	private boolean isArtistProfile;
	private String phoneNumber;
	private String emailAddress;
	private UserDto user;
	private String password;
	
	public ProfileDto() {
	}
	
	public ProfileDto(String username, boolean isArtistProfile, String phoneNumber, String emailAddress, UserDto user, String password) {
		
		this.username = username;
		this.isArtistProfile = isArtistProfile;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.user = user;
		this.password = password;
	}
	
	public void setUsername(String value) {
		this.username = value;
	}

	public String getUsername() {
		return username;
	}
	
	public void setIsArtistProfile(boolean value) {
		this.isArtistProfile = value;
	}
	
	public boolean isIsArtistProfile() {
		return isArtistProfile;
	}
	public void setPhoneNumber(String value) {
		this.phoneNumber = value;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setEmailAddress(String value) {
		this.emailAddress = value;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return this.password;
	}
}
