package ca.mcgill.ecse321.artgallery.model;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

@Entity
@Table(name="users")
public class User{
	
	@Column
	private String name;

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}

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
	private UserRole userRole;
	
	@OneToOne(mappedBy="user" , cascade={CascadeType.ALL}, optional=true)
	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	
	private Profile profile;
	
	@OneToOne(mappedBy="user" , cascade={CascadeType.ALL}, optional=true)
	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	
	private Inventory inventory;

	@ManyToOne(optional=true)
	public Inventory getInventory() {
		return this.inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}



}
