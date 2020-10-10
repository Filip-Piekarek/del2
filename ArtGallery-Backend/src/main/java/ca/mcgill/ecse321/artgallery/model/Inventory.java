package ca.mcgill.ecse321.artgallery.model;

import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Inventory{
	
	@Id
	private long id;

	public void setId(long value) {
		this.id = value;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return this.id;
	}

	
	private Set<User> users;

	@OneToMany(mappedBy="inventory")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> userss) {
		this.users = userss;
	}

	
	private Set<Posting> postings;

	@OneToMany(mappedBy="inventory" , cascade={CascadeType.ALL})
	public Set<Posting> getPostings() {
		return this.postings;
	}

	public void setPostings(Set<Posting> postingss) {
		this.postings = postingss;
	}
}
