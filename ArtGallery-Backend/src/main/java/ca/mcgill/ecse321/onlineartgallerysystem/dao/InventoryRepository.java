package ca.mcgill.ecse321.onlineartgallerysystem.dao;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.onlineartgallerysystem.model.Inventory;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Posting;
import ca.mcgill.ecse321.onlineartgallerysystem.model.User;

@Repository
public class InventoryRepository {

	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public Inventory createInventory(Set<User> users, Set<Posting> postings) {
		Inventory inventory = new Inventory();
		inventory.setUsers(users);
		inventory.setPostings(postings);
		entityManager.persist(inventory);
		return inventory;
	}
	@Transactional
	public Inventory getInventory() {
		return null;
	}
}
