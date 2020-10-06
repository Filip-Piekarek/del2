package ca.mcgill.ecse321.onlineartgallerysystem.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.onlineartgallerysystem.model.Inventory;
import ca.mcgill.ecse321.onlineartgallerysystem.model.OnlineArtGallerySystem;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Profile;
import ca.mcgill.ecse321.onlineartgallerysystem.model.User;
import ca.mcgill.ecse321.onlineartgallerysystem.model.UserRole;

@Repository
public class UserRepository {

	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public User createUser(long userId, String name, UserRole userRole, Inventory inventory, OnlineArtGallerySystem system, Profile profile) {
		
		User user = new User();
		user.setId(userId);
		user.setName(name);
		user.setUserRole(userRole);
		user.setInventory(inventory);
		user.setOnlineArtGallerySystem(system);
		user.setProfile(profile);
		user.setSystem(system);
		entityManager.persist(user);
		return user;
	}
	
	@Transactional
	public User getUser(long userId) {
		
		User user = entityManager.find(User.class, userId);
		return user;
	}
}