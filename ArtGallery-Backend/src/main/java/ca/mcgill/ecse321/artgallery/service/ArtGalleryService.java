package ca.mcgill.ecse321.artgallery.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.artgallery.dao.ArtworkCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.OrderCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.PostingCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.ProfileCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.UserCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.UserRoleCrudRepository;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Client;
import ca.mcgill.ecse321.artgallery.model.Order;
import ca.mcgill.ecse321.artgallery.model.OrderStatus;
import ca.mcgill.ecse321.artgallery.model.Posting;
import ca.mcgill.ecse321.artgallery.model.User;
import ca.mcgill.ecse321.artgallery.model.UserRole;

@Service
public class ArtGalleryService {
	
	@Autowired
	private UserCrudRepository userRepo;
	@Autowired 
	private UserRoleCrudRepository userRoleRepo;
	@Autowired
	private ProfileCrudRepository profileRepo;
	@Autowired
	private ArtworkCrudRepository artworkRepo;
	@Autowired
	private PostingCrudRepository postingRepo;
	@Autowired
	private OrderCrudRepository orderRepo;

	//Service methods for Artist
	@Transactional
	public Artist createArtist(User user, String bio) {
		
		// Create artist userRole
		Artist artist = new Artist();
		artist.setBiography(bio);
		artist.setUser(user);
				
		// Save artist in repository
		userRoleRepo.save(artist);
		
		return artist;
	}
	
	@Transactional
	public Artist getArtist(long id) {
		 Artist artist = (Artist) userRoleRepo.findUserRoleById(id);
		 return artist;
	}
	
	//Service methods for Order
	@Transactional
	public Order createOrder(Client client, Posting posting) {
		
		// Create order
		Order order = new Order();
		order.setClient(client);
		order.setInStorePickUp(true);
		order.setOrderStatus(OrderStatus.IN_PROCESS);
			
		// Create set of postings for the order
		Set<Posting> items = new HashSet<Posting>();
		order.setItems(items);
				
		// Save order in repository
		orderRepo.save(order);
		
		return order;
	}
	
	@Transactional
	public Order getOrder(long orderId) {
		
		Order order = orderRepo.findOrderById(orderId);
		return order;
	}
}
