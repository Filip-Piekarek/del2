package ca.mcgill.ecse321.artgallery.model;


import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class OnlineArtGallerySystem{
	@Id
	private long id;

	public void setId(long value) {
		this.id = value;
	}
	@Id
	public long getId() {
		return this.id;
	}
	private Set<User> users;

	@OneToMany(mappedBy="onlineArtGallerySystem" , cascade={CascadeType.ALL})
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> userss) {
		this.users = userss;
	}

}
