package ca.mcgill.ecse321.artgallery.service;

import java.sql.Date;
import java.util.HashSet;
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
import ca.mcgill.ecse321.artgallery.model.Address;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.ArtworkType;
import ca.mcgill.ecse321.artgallery.model.Client;
import ca.mcgill.ecse321.artgallery.model.Order;
import ca.mcgill.ecse321.artgallery.model.OrderStatus;
import ca.mcgill.ecse321.artgallery.model.Posting;
import ca.mcgill.ecse321.artgallery.model.Profile;
import ca.mcgill.ecse321.artgallery.model.User;

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

	//Service methods for User
	@Transactional
	public User createUser(String name) {
		
		//Create user
		User user = new User();
		user.setName(name);
		
		//Save user in repository
		userRepo.save(user);
		return user;
	}
	
	@Transactional
	public User getUser(long id) {
		User user = userRepo.findUserById(id);
		return user;
	}
	
	@Transactional
	public Set<User> getAllUsers() {
		return toSet(userRepo.findAll());
	}
	
	//Service methods for profile
	@Transactional
	public Profile createProfile(User user, String username, String password, String email, String phone, boolean isArtist) {
		
		//Create Profile
		Profile profile = new Profile();
		profile.setUser(user);
		profile.setUsername(username);
		profile.setPassword(password);
		profile.setEmailAddress(email);
		profile.setPhoneNumber(phone);
		profile.setIsArtistProfile(isArtist);
		
		//Save profile in repository
		profileRepo.save(profile);
		
		return profile;
	}
	
	@Transactional
	public Profile getProfile(String username) {
		return profileRepo.findProfileByUsername(username);
	}
	
	@Transactional
	public Set<Profile> getAllProfile() {
		return toSet(profileRepo.findAll());
	}
	
	//Service methods for Client
	@Transactional
	public Client createClient(User user) {
		
		//Create Client userRole
		Client client = new Client();
		Set<Address> addresses = new HashSet<Address>();
		client.setAddresses(addresses);
		client.setUser(user);
		
		//Save client in repository
		userRoleRepo.save(client);
		
		return client;
	}
	
	@Transactional
	public Client getClient(long id) {
		return (Client) userRoleRepo.findUserRoleById(id);
	}
	
	@Transactional
	public Set<Client> getAllClient() {
		
		Set<Client> clients = new HashSet<Client>();
		for (Profile profile : profileRepo.findAll()) {
			if (!profile.isIsArtistProfile()) {
				clients.add((Client) profile.getUser().getUserRole());
			}
		}
		return clients;
	}
	
	//Service methods for Artist
	@Transactional
	public Artist createArtist(User user, String bio) {
		
		// Create artist userRole
		Artist artist = new Artist();
		Set<Artwork> artworks = new HashSet<Artwork>();
		artist.setBiography(bio);
		artist.setUser(user);
		artist.setArtworks(artworks);
				
		// Save artist in repository
		userRoleRepo.save(artist);
		
		return artist;
	}
	
	@Transactional
	public Artist getArtist(long id) {
		 Artist artist = (Artist) userRoleRepo.findUserRoleById(id);
		 return artist;
	}
	
	@Transactional
	public Set<Artist> getAllArtists() {
		Set<Artist> artists = new HashSet<Artist>();
		for (Profile profile : profileRepo.findAll()) {
			if (profile.isIsArtistProfile()) {
				artists.add((Artist) profile.getUser().getUserRole());
			}
		}
		return artists;
	}
	
	//Service methods for Artwork
	@Transactional
	public Artwork createArtwork(Artist artist, String name, String description, Date date, ArtworkType type) {
		
		//Create artwork
		Artwork artwork = new Artwork();
		artwork.setName(name);
		artwork.setDescription(description);
		artwork.setCreator(artist);
		artwork.setArtworkType(type);
		artwork.setDate(date);
		artist.addArtwork(artwork);
		
		//Save artwork in repository
		artworkRepo.save(artwork);
		
		return artwork;
	}
	
	@Transactional
	public Artwork getArtwork(long id) {
		
		return artworkRepo.findArtworkById(id);
	}
	
	@Transactional
	public Set<Artwork> getAllArtworks() {
		
		return toSet(artworkRepo.findAll());
	}
	
	//Service methods for Posting
	@Transactional
	public Posting createPosting(double price, boolean visibility, Artwork artwork, boolean online, int priority) {
		
		//Create posting
		Posting post = new Posting();
		post.setVisibility(visibility);
		post.setItem(artwork);
		post.setPrice(price);
		post.setIsOnline(online);
		post.setPriority(priority);
		
		//Save posting in repository
		postingRepo.save(post);
		
		return post;
	}
	
	@Transactional
	public Posting getPosting(long id) {
		return postingRepo.findPostingById(id);
	}
	
	@Transactional
	public Set<Posting> getAllPostings() {
		return toSet(postingRepo.findAll());
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
	
	@Transactional
	public Set<Order> getAllOrders() {
		return toSet(orderRepo.findAll());
	}
	
	//Iterable to Set
	private <T> Set<T> toSet(Iterable<T> iterable){
		Set<T> resultList = new HashSet<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
