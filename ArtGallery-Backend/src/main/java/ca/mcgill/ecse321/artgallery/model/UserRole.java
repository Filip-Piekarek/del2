package ca.mcgill.ecse321.artgallery.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public abstract class UserRole{
	@Id
	private User user;

	@Id
	@OneToOne(optional=false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
