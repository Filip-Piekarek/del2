package ca.mcgill.ecse321.artgallery.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.onlineartgallerysystem.dao.ArtistCrudRepository;
import ca.mcgill.ecse321.onlineartgallerysystem.dao.ArtworkCrudRepository;
import ca.mcgill.ecse321.onlineartgallerysystem.dao.ClientCrudRepository;
import ca.mcgill.ecse321.onlineartgallerysystem.dao.InventoryCrudRepository;
import ca.mcgill.ecse321.onlineartgallerysystem.dao.OrderCrudRepository;
import ca.mcgill.ecse321.onlineartgallerysystem.dao.PostingCrudRepository;
import ca.mcgill.ecse321.onlineartgallerysystem.dao.ProfileCrudRepository;
import ca.mcgill.ecse321.onlineartgallerysystem.dao.UserCrudRepository;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Artist;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Inventory;
import ca.mcgill.ecse321.onlineartgallerysystem.model.OnlineArtGallerySystem;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Profile;
import ca.mcgill.ecse321.onlineartgallerysystem.model.User;
import ca.mcgill.ecse321.onlineartgallerysystem.model.UserRole;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration
public class TestUserPersistence {
	
	public User setup() {
		OnlineArtGallerySystem system = new OnlineArtGallerySystem();
		User user1 = new User();
		Inventory inventory = new Inventory();
		Profile profile = new Profile();
		UserRole artist = new Artist();
		user1.setUserRole(artist);
		user1.setInventory(inventory);
		user1.setProfile(profile);
		return user1;
	
	}
	
	
	
	@Autowired 
	private UserCrudRepository userRepo;
	@Autowired 
	private ArtistCrudRepository artistRepo;
	@Autowired 
	private ClientCrudRepository clientRepo;
	@Autowired 
	private ArtworkCrudRepository artworkRepo;
	@Autowired 
	private InventoryCrudRepository inventoryRepo;
	@Autowired 
	private OrderCrudRepository orderRepo;
	@Autowired 
	private PostingCrudRepository postingRepo;
	@Autowired 
	private ProfileCrudRepository profileRepo;
	
	
	long ID;
	String name;
	
	
	
	  	
	
	/*@AfterEach
	public void clearDatabase() {

		artistRepo.deleteAll();		
		artworkRepo.deleteAll();
		inventoryRepo.deleteAll();
		orderRepo.deleteAll();
		postingRepo.deleteAll();
		profileRepo.deleteAll();
		userRepo.deleteAll();
	}
	*/
	@BeforeEach void init() {
		
		ID = 1818181818;
		name = "mike smith";
	}
	@Test public void testPersistAndLoadSystem() {
		
	}
	@Test void test() {
		User user1 = setup();
		
			//user1.setSystem(system);
			user1.setId(ID);
			user1.setName(name);
			
			
			//user1.setOnlineArtGallerySystem(system);
			
			
			userRepo.save(user1);
			
		
		assertNotNull(user1);
		
		
		User user2 = userRepo.findUserById(ID);
		assertNotNull(user2);
		assertEquals(name,user2.getName());
		assertEquals(ID,user2.getId());
		
		

	}
	
	
	
	

}
