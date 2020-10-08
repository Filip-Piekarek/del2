package ca.mcgill.ecse321.onlineartgallerysystem.dao;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.onlineartgallerysystem.model.Artwork;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Inventory;
import ca.mcgill.ecse321.onlineartgallerysystem.model.OnlineArtGallerySystem;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Order;
import ca.mcgill.ecse321.onlineartgallerysystem.model.OrderStatus;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Posting;

@Repository
public class PostingRepository {
	
	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public Posting createPosting(Order order, OnlineArtGallerySystem system, Artwork art, long id, boolean pickup, OrderStatus status, boolean visible, Inventory inventory) {
		Posting post = new Posting();
		post.setId(id);
		post.setInStorePickUp(pickup);
		post.setInventory(inventory);
		post.setItem(art);
		post.setOrder(order);
		post.setOrderStatus(status);
		post.setSystem(system);
		post.setVisibility(visible);
		entityManager.persist(post);
		return post;
	}
	
	@Transactional
	public Posting getPosting(long id) {
		Posting post = entityManager.find(Posting.class, id);
		return post;
	}
}
