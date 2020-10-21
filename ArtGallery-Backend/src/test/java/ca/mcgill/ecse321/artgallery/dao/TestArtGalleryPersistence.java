package ca.mcgill.ecse321.artgallery.dao;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.artgallery.dao.ArtworkCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.OrderCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.PostingCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.ProfileCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.UserCrudRepository;
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


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestArtGalleryPersistence {

	@Autowired
	private UserCrudRepository userRepo;
	@Autowired 
	private UserRoleCrudRepository userRoleRepo;
	@Autowired
	private AddressCrudRepository addressRepo;
	@Autowired
	private ProfileCrudRepository profileRepo;
	@Autowired
	private ArtworkCrudRepository artworkRepo;
	@Autowired
	private PostingCrudRepository postingRepo;
	@Autowired
	private OrderCrudRepository orderRepo;


	@AfterEach
	public void clearDatabase() {
		orderRepo.deleteAll();
		postingRepo.deleteAll();
		artworkRepo.deleteAll();
		profileRepo.deleteAll();
		addressRepo.deleteAll();
		userRoleRepo.deleteAll();
		userRepo.deleteAll();

	}

	@Test
	public void testPersistAndLoadUser() {

		// Create user to be persisted
		String name = "Neil";
		User user = new User();
		user.setName(name);

		// Save user in the repository
		userRepo.save(user);
		long userId = user.getId();

		// Set the user object to null
		user = null;

		// Fetch the user in the repository using his Id
		user = userRepo.findUserById(userId);

		// Check if the user object is not null
		assertNotNull(user);

		// Check if the correct user was recovered
		assertEquals(name, user.getName());
		assertEquals(userId, user.getId());
	}

	@Test
	public void testPersistAndLoadArtist() {

		// Create user object
		String name = "name";
		User user = new User();
		user.setName(name);

		// Save user in repository
		userRepo.save(user);
		long userId = user.getId();

		// Create artist userRole object
		Artist artist = new Artist();
		String bio = "bio";
		artist.setUser(user);
		artist.setBiography(bio);

		// Save artist in userRole repository
		userRoleRepo.save(artist);
		long artistId = artist.getId();

		// Set objects to null
		user = null;
		artist = null;

		// Fetch objects in repositories with corresponding IDs
		user = userRepo.findUserById(userId);
		artist = (Artist) userRoleRepo.findUserRoleById(artistId);

		// Check if objects are not null
		assertNotNull(user);
		assertNotNull(artist);

		// Assert the recovered objects are the right ones
		assertEquals(userId, user.getId());

		assertEquals(artistId, artist.getId());

		assertEquals(bio, artist.getBiography());

	}

	@Test
	public void testPersistAndLoadClient() {

		// Create user object
		String name = "name";
		User user = new User();
		user.setName(name);

		// Save user in repository
		userRepo.save(user);
		long userId = user.getId();

		// Create client userRole object
		Client client = new Client();

		// Create set of addresses for client
		Set<Address> addresses = new HashSet<Address>();
		client.setAddresses(addresses);

		// Create new address
		Address address = new Address();
		address.setStreetNumber(4728);
		address.setStreetName("Peel St");
		address.setCity("Montreal");
		address.setProvince("Quebec");
		address.setCountry("CANADA");
		address.setPostalCode("H4A 1C4");
		address.setClient(client);

		client.setUser(user);
		client.getAddresses().add(address);
		
		// Save client in userRole repository
		userRoleRepo.save(client);
		long id = client.getId();

		// Save address in Address repository
		addressRepo.save(address);
		long addressId = address.getId();

		// Set all objects to null
		address = null;
		client = null;
		user = null;

		// Recover objects in the repositories
		user = userRepo.findUserById(userId);
		client = (Client) userRoleRepo.findUserRoleById(id);
		address = addressRepo.findAddressById(addressId);



		// Assert user is not null and has the correct id
		assertNotNull(user);
		assertEquals(userId, user.getId());

		// Assert client is not null, and is the corresponding client
		assertNotNull(client);
		assertEquals(id, client.getId());
		assertEquals(address.getClient().getId(), client.getId());

	}

	@Test
	public void testPersistAndLoadAddress() {

		// Create user object
		String name = "name";
		User user = new User();
		user.setName(name);

		// Save user in repository
		userRepo.save(user);
		long userId = user.getId();

		// Create client userRole object
		Client client = new Client();

		// Create set of addresses for client
		Set<Address> addresses = new HashSet<Address>();
		client.setAddresses(addresses);

		// Create new address
		Address address = new Address();
		address.setStreetNumber(4728);
		address.setStreetName("Peel St");
		address.setCity("Montreal");
		address.setProvince("Quebec");
		address.setCountry("CANADA");
		address.setPostalCode("H4A 1C4");
		address.setClient(client);

		client.setUser(user);
		client.getAddresses().add(address);

		// Save client in userRole repository
		userRoleRepo.save(client);
		long clientId = client.getId();

		// Save address in Address repository
		addressRepo.save(address);
		long addressId = address.getId();



		// Set all objects to null
		address = null;
		client = null;
		user = null;

		// Find the objects in the repositories
		user = userRepo.findUserById(userId);
		client = (Client) userRoleRepo.findUserRoleById(clientId);
		address = addressRepo.findAddressById(addressId);

		assertNotNull(user);
		assertNotNull(client);
		assertNotNull(address);

		assertEquals(addressId, address.getId());
		assertEquals(4728, address.getStreetNumber());
		assertEquals("Peel St", address.getStreetName());
		assertEquals("Montreal", address.getCity());
		assertEquals("Quebec", address.getProvince());
		assertEquals("CANADA", address.getCountry());
		assertEquals("H4A 1C4", address.getPostalCode());
		assertEquals(client.getId(), address.getClient().getId());		


	}


	@Test
	public void testPersistAndLoadProfile() {

		// Create user object
		String name = "name";
		User user = new User();
		user.setName(name);

		// Persist user object in the repository
		userRepo.save(user);
		long userId = user.getId();

		// Create profile
		String email = "neil.banik@mail.mcgill.ca";
		String username = "neilbanik";
		String phone = "1-800-696-9420";
		String password = "**********";
		Profile profile = new Profile();
		profile.setEmailAddress(email);
		profile.setIsArtistProfile(true);
		profile.setPassword(password);
		profile.setPhoneNumber(phone);
		profile.setUser(user);
		profile.setUsername(username);

		// Save profile in repository
		profileRepo.save(profile);

		// Set objects to null
		profile = null;
		user = null;

		// Recover objects from repositories
		user = userRepo.findUserById(userId);
		profile = profileRepo.findProfileByUsername(username);

		// Assertions to check if the recovered objects are not null
		// and correspond to query
		assertNotNull(user);
		assertEquals(userId, user.getId());
		assertNotNull(profile);
		assertEquals(email, profile.getEmailAddress());
		assertEquals(username, profile.getUsername());
		assertEquals(phone, profile.getPhoneNumber());
		assertEquals(password, profile.getPassword());

	}

	@Test
	public void testPersistAndLoadArtwork() {

		// Create user
		String name = "Thais";
		User user = new User();
		user.setName(name);

		// Persist user in database
		userRepo.save(user);
		long userId = user.getId();

		// Create userRole of artist
		Artist artist = new Artist();
		String bio = "bio";
		artist.setUser(user);
		artist.setBiography(bio);

		// Create set of artworks for the artist
		Set<Artwork> artworks = new HashSet<Artwork>();
		artist.setArtworks(artworks);

		// Save artist in userRole repository
		userRoleRepo.save(artist);
		long artistId = artist.getId();

		// Create artwork
		Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.APRIL, 20));
		String paintingName = "Joconde";
		String description = "Louvre";
		Artwork artwork = new Artwork();
		artwork.setDescription(description);
		artwork.setArtworkType(ArtworkType.FRESCO_PAINTING);
		artwork.setName(paintingName);
		artwork.setDate(date);
		artwork.setCreator(artist);
		artist.addArtwork(artwork);

		// Save artwork in the repository
		artworkRepo.save(artwork);
		long artId = artwork.getId();

		// Set all the objects to null
		artwork = null;
		artist = null;
		user = null;

		// Recover all objects by fetching inside the repositories
		user = userRepo.findUserById(userId);
		artist = (Artist) userRoleRepo.findUserRoleById(artistId);
		artwork = artworkRepo.findArtworkById(artId);

		// Assert objects are not null
		assertNotNull(user);
		assertNotNull(artist);
		assertNotNull(artwork);

		// Assert objects correspond to query 
		assertEquals(userId, user.getId());
		assertEquals(artistId, artist.getId());

		assertEquals(artId, artwork.getId());
		assertEquals(date, artwork.getDate());
		assertEquals(description, artwork.getDescription());
		assertEquals(paintingName, artwork.getName());
		assertEquals(artist.getId(), artwork.getCreator().getId());

	}


	@Test
	public void testPersistAndLoadPosting() {

		// Create user
		String name = "Zoé";
		User user = new User();
		user.setName(name);

		// Save user
		userRepo.save(user);
		long userId = user.getId();

		// Create Artist
		Artist artist = new Artist();
		String bio = "bio";
		artist.setUser(user);
		artist.setBiography(bio);
		Set<Artwork> artworks = new HashSet<Artwork>();
		artist.setArtworks(artworks);

		// Save artist
		userRoleRepo.save(artist);
		long artistId = artist.getId();

		// Create artwork
		Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.APRIL, 20));
		String paintingName = "Joconde";
		String description = "Louvre";
		Artwork artwork = new Artwork();
		artwork.setDescription(description);
		artwork.setArtworkType(ArtworkType.FRESCO_PAINTING);
		artwork.setName(paintingName);
		artwork.setDate(date);
		artwork.setCreator(artist);
		artist.addArtwork(artwork);

		// Save artwork
		artworkRepo.save(artwork);
		long artId = artwork.getId();

		// Create posting
		double price = 5000;
		Posting post = new Posting();
		post.setVisibility(true);
		post.setItem(artwork);
		post.setPrice(price);

		// Persist posting in the database
		postingRepo.save(post);
		long postId = post.getId();

		// Set all objects to null
		post = null;
		artwork = null;
		artist = null;
		user = null;

		// Recover objects from the database
		user = userRepo.findUserById(userId);
		artist = (Artist) userRoleRepo.findUserRoleById(artistId);
		artwork = artworkRepo.findArtworkById(artId);
		post = postingRepo.findPostingById(postId);

		// Assert they are not null
		assertNotNull(user);
		assertNotNull(artist);
		assertNotNull(artwork);
		assertNotNull(post);

		// Check if objects correspond to query
		assertEquals(postId, post.getId());
		assertEquals(post.getItem().getId(), artwork.getId());

	}


	@Test
	public void testPersistAndLoadOrder() {

		// Create first user for client
		String clientName = "Morgane";
		User clientUser = new User();
		clientUser.setName(clientName);

		// Save first user in repository
		userRepo.save(clientUser);
		long userClientId = clientUser.getId();

		// Create second user for artist
		String artistName = "Alexandra";
		User artistUser = new User();
		artistUser.setName(artistName);

		// Save second user in repository
		userRepo.save(artistUser);
		long userArtistId = artistUser.getId();

		// Create client userRole
		Client clientRole = new Client();
		
		// Create set of addresses for client
		Set<Address> addresses = new HashSet<Address>();
		clientRole.setAddresses(addresses);

		// Create new address
		Address address = new Address();
		address.setStreetNumber(4728);
		address.setStreetName("Peel St");
		address.setCity("Montreal");
		address.setProvince("Quebec");
		address.setCountry("CANADA");
		address.setPostalCode("H4A 1C4");
		address.setClient(clientRole);

		clientRole.setUser(clientUser);
		clientRole.getAddresses().add(address);

		// Save client in repository
		userRoleRepo.save(clientRole);
		long clientId = clientRole.getId();

		// Save address in Address repository
		addressRepo.save(address);
		long addressId = address.getId();

		// Create artist userRole
		String bio = "design";
		Artist artistRole = new Artist();
		artistRole.setBiography(bio);
		artistRole.setUser(artistUser);

		// Save artist in repository
		userRoleRepo.save(artistRole);
		long artistId = artistRole.getId();

		// Create set for the artist's artworks
		Set<Artwork> artworks = new HashSet<Artwork>();
		artistRole.setArtworks(artworks);

		// Create first artwork
		Date date = java.sql.Date.valueOf(LocalDate.of(2000, Month.NOVEMBER, 2));
		String paintingName = "The Starry Night";
		String description ="New York MoMa";
		Artwork item = new Artwork();
		item.setArtworkType(ArtworkType.ACRYLIC_PAINTING);
		item.setCreator(artistRole);
		artistRole.addArtwork(item);
		item.setDate(date);
		item.setDescription(description);
		item.setName(paintingName);

		// Save artwork inside repository
		artworkRepo.save(item);
		long artworkId = item.getId();

		// Create first posting
		Posting post = new Posting();
		post.setVisibility(true);
		post.setItem(item);
		post.setPrice(5000);

		// Create second artwork
		Date date2 = java.sql.Date.valueOf(LocalDate.of(2003, Month.AUGUST, 27));
		String paintingName2 = "No Idea";
		String description2 ="Cubism";
		Artwork item2 = new Artwork();
		item2.setArtworkType(ArtworkType.DRAWING);
		item2.setCreator(artistRole);
		artistRole.addArtwork(item2);
		item2.setDate(date2);
		item2.setDescription(description2);
		item2.setName(paintingName2);


		// Save artwork inside repository
		artworkRepo.save(item2);
		long artwork2Id = item.getId();

		// Create second posting
		Posting post2 = new Posting();
		post2.setVisibility(true);
		post2.setItem(item2);
		post2.setPrice(50300);


		// Create order
		Order order = new Order();
		order.setClient(clientRole);
		order.setInStorePickUp(true);
		order.setOrderStatus(OrderStatus.IN_PROCESS);

		// Create set of postings for the order
		Set<Posting> items = new HashSet<Posting>();
		order.setItems(items);

		// Save order in repository
		orderRepo.save(order);
		long orderId = order.getId();

		// Link order to posting
		items.add(post);
		items.add(post2);
		post.setOrder(order);
		post2.setOrder(order);

		// Save postings inside repository
		postingRepo.save(post);
		long postId = post.getId();
		postingRepo.save(post2);
		long post2Id = post2.getId();


		// Set all objects to null
		address = null;
		order = null;
		post = null;
		post2 = null;
		item = null;
		item2 = null;
		clientRole = null;
		artistRole = null;
		clientUser = null;
		artistUser = null;


		// Fetch all objects in the repositories
		artistUser = userRepo.findUserById(userArtistId);
		clientUser = userRepo.findUserById(userClientId);
		artistRole = (Artist) userRoleRepo.findUserRoleById(artistId);
		clientRole = (Client) userRoleRepo.findUserRoleById(clientId);
		address = addressRepo.findAddressById(addressId);
		item = artworkRepo.findArtworkById(artworkId);
		item2 = artworkRepo.findArtworkById(artwork2Id);
		post = postingRepo.findPostingById(postId);
		post2 = postingRepo.findPostingById(post2Id);
		order = orderRepo.findOrderById(orderId);

		// Check that all objects are not null
		assertNotNull(artistUser);
		assertNotNull(clientUser);
		assertNotNull(artistRole);
		assertNotNull(clientRole);
		assertNotNull(address);
		assertNotNull(item);
		assertNotNull(item2);
		assertNotNull(post);
		assertNotNull(post2);
		assertNotNull(order);

		assertEquals(orderId, order.getId());
		assertEquals(post.getOrder().getId(), order.getId());
		assertEquals(post2.getOrder().getId(), order.getId());
		assertEquals(order.getClient().getId(), clientRole.getId());



	}
}