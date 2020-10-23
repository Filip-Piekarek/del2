package ca.mcgill.ecse321.artgallery.dto;

public class UserRoleDto {

	private long id;
	private UserDto user;
	
	public UserRoleDto() {
	}
	
	public UserRoleDto(long id, UserDto user) {
		this.id = id;
		this.user = user;
	}
	
	public void setId(long value) {
		this.id = value;
	}
	
	public long getId() {
		return this.id;
	}
	
	public UserDto getUser() {
		return this.user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
}
