package ca.mcgill.ecse321.artgallery.dao;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.artgallery.dao.ArtistCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtworkCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.ClientCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.InventoryCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.OrderCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.PostingCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.ProfileCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.UserCrudRepository;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.ArtworkType;
import ca.mcgill.ecse321.artgallery.model.Client;
import ca.mcgill.ecse321.artgallery.model.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestArtGalleryPersistence {
	
	@Autowired
	private UserCrudRepository userRepo;
	@Autowired 
	private UserRoleCrudRepository userRoleRepo;
	@Autowired
	private ArtistCrudRepository artistRepo;
	@Autowired
	private ClientCrudRepository clientRepo;
	@Autowired
	private ProfileCrudRepository profileRepo;
	@Autowired
	private InventoryCrudRepository inventoryRepo;
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
		inventoryRepo.deleteAll();
		
		profileRepo.deleteAll();
		artistRepo.deleteAll();
		clientRepo.deleteAll();
		userRoleRepo.deleteAll();
		userRepo.deleteAll();
		
	}
	
	@Test
    public void testPersistAndLoadUser() {
    
        String name = "Neil";
        User user1 = new User();
        //Profile profile = new Profile();
        //Inventory inventory = new Inventory();
        //Client client = new Client();
        user1.setName(name);
        //user1.setProfile(profile);
        //user1.setUserRole(client);
        //user1.setInventory(inventory);
        userRepo.save(user1);
        long userId = user1.getId();

        user1 = null;

        user1 = userRepo.findUserById(userId);
        assertNotNull(user1);
        assertEquals(name, user1.getName());
        //assertEquals(inventory, user1.getInventory());
        assertEquals(name, user1.getName());
        assertEquals(name, user1.getName());
        assertEquals(userId, user1.getId());
    }
	@Test
    public void testPersistAndLoadArtist() {
        
        String name = "name";
        User user2 = new User();
        System.err.println("BONK");
        Artist artist2 = new Artist();
        System.err.println("BONK1");
        String bio = "bio";
        System.err.println("BONK2");
        user2.setName(name);
        System.err.println("BONK3");
        //user2.setUserRole(artist2);
        System.err.println("BONK4");
        userRepo.save(user2);
        
        long userId = user2.getId();
        System.err.println("BONK5");
        artist2.setUser(user2);
        System.err.println("BONK6");
        artist2.setBiography(bio);
        System.err.println("BONK7");
        artistRepo.save(artist2);
        System.err.println("BONK8");
        long id = artist2.getId();
        
        
        
        artist2 = null;
        user2 = null;
        
        user2 = userRepo.findUserById(userId);
        artist2 = artistRepo.findArtistById(id);
        
        assertNotNull(artist2);
        assertEquals(id, artist2.getId());
        assertEquals(bio, artist2.getBiography());
        
    }
	@Test
    public void testPersistAndLoadClient() {
        String name = "name";
        User user3 = new User();
        Client client3 = new Client();
        String address = "address";
        user3.setName(name);
        userRepo.save(user3);
        long userId = user3.getId();
        
        client3.setUser(user3);
        client3.setDeliveryAddress(address);
        clientRepo.save(client3);
        long id = client3.getId();
        
        client3 = null;
        user3 = null;
        
        user3 = userRepo.findUserById(userId);
        client3 = clientRepo.findClientById(id);
        
        assertNotNull(client3);
        assertEquals(id, client3.getId());
        assertEquals(address, client3.getDeliveryAddress());
    }
	@Test
    public void testPersistAndLoadArtwork() {
		
		Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.APRIL, 20));
        String name = "name";
        String paintingName = "YEET";
        String description ="MHHHHHHHMMMM veryyy nice artwooooork";
        
        User user2 = new User();
        user2.setName(name);
        userRepo.save(user2);
        long userId = user2.getId();
        System.err.println("BONK");

        Artist artist2 = new Artist();
        String bio = "bio";
        artist2.setUser(user2);
        artist2.setBiography(bio);
        artistRepo.save(artist2);
        long id = artist2.getId();
        System.err.println("BONK1");
        

        Artwork artwork = new Artwork();
        artwork.setCreator(artist2);
        artwork.setDescription(description);
        artwork.setArtworkType(ArtworkType.FRESCO_PAINTING);
        artwork.setName(paintingName);
        artwork.setDate(date);
        System.err.println("BONK2");

       
        artworkRepo.save(artwork);
        System.err.println("BONK3");

        long artid = artwork.getId();
        System.err.println("BONK4");

        artwork = null;
        artist2 = null;
        user2 = null;
        
        
        user2 = userRepo.findUserById(userId);
        artist2 = artistRepo.findArtistById(id);
        artwork = artworkRepo.findArtworkById(artid);
        
        assertNotNull(artwork);
        assertEquals(artid, artwork.getId());
        assertEquals(date, artwork.getDate());
        assertEquals(description, artwork.getDescription());
        assertEquals(paintingName, artwork.getName());
        assertEquals(artist2, artwork.getCreator());




        
    }
}