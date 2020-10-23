package ca.mcgill.ecse321.artgallery.dto;

public class UserDto {

	private String name;
	private long id;
	private UserRoleDto userRole;
	private ProfileDto profile;
	
	public UserDto() {
	}
	
	public UserDto(String name, long id, UserRoleDto userRole, ProfileDto profile) {
		this.name = name;
		this.id = id;
		this.userRole = userRole;
		this.profile = profile;
	}
	
	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return name;
	}
	
	public void setId(long value) {
		this.id = value;
	}
	
	public long getId() {
		return id;
	}
	
	public UserRoleDto getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoleDto userRole) {
		this.userRole = userRole;
	}
	
	public ProfileDto getProfile() {
		return this.profile;
	}

	public void setProfile(ProfileDto profile) {
		this.profile = profile;
	}
}
