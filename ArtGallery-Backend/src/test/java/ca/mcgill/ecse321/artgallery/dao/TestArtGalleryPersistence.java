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
		userRepo.deleteAll();
		
	}

	@Test
	public void testPersistAndLoadUser() {
	
		
		String name = "Neil";
		long userId = 345345435;
		User user = new User();
		user.setName(name);
		user.setId(userId);
		userRepo.save(user);

		user = null;

		user = userRepo.findUserById(userId);
		assertNotNull(user);
		assertEquals(name, user.getName());
		assertEquals(userId, user.getId());
	}
	
//	@Test
//	public void testCreateUser() {
//		
//		assertEquals(0, service.getAllUser().size());
//
//		String name = "Neil";
//
//		try {
//			service.createUser(name);
//		} catch (IllegalArgumentException e) {
//
//			fail();
//		}
//
//		List<User> allUsers = service.getAllUser();
//
//		assertEquals(1, allUsers.size());
//		assertEquals(name, allUsers.get(0).getName());
//	}
	
	/*@Test
	public void testPersistAndLoadArtwork() {
		String name = "Artwork_test";
		Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.APRIL, 20));
		ArtworkType type = SPRAY_PAINT;
		
		assertNotNull(event);
		assertEquals(name, event.getName());
		assertEquals(date, event.getDate());
		assertEquals(startTime, event.getStartTime());
		assertEquals(endTime, event.getEndTime());
	}

	@Test
	public void testPersistAndLoadRegistration() {
		String personName = "TestPerson";
		Person person = new Person();
		person.setName(personName);
		personRepository.save(person);

		String eventName = "ECSE321 Tutorial";
		Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
		Event event = new Event();
		event.setName(eventName);
		event.setDate(date);
		event.setStartTime(startTime);
		event.setEndTime(endTime);
		eventRepository.save(event);

		Registration reg = new Registration();
		int regId = 1;
		// First example for reference save/load
		reg.setId(regId);
		reg.setPerson(person);
		reg.setEvent(event);
		registrationRepository.save(reg);

		reg = null;

		reg = registrationRepository.findByPersonAndEvent(person, event);
		assertNotNull(reg);
		assertEquals(regId, reg.getId());
		// Comparing by keys
		assertEquals(person.getName(), reg.getPerson().getName());
		assertEquals(event.getName(), reg.getEvent().getName());
	}*/

}