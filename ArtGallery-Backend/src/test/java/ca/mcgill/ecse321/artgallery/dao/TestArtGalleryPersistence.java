/*package ca.mcgill.ecse321.artgallery.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.onlineartgallerysystem.dao.UserRepository;
import ca.mcgill.ecse321.onlineartgallerysystem.model.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestArtGalleryPersistence {

	@Autowired
	private UserRepository userRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		//registrationRepository.deleteAll();
		// Then we can clear the other tables
		userRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadPerson() {
		String name = "TestUser";
		// First example for object save/load
		User user = new User();
		// First example for attribute save/load
		user.setName(name);
		userRepository.save(user);

		person = null;

		person = personRepository.findPersonByName(name);
		assertNotNull(person);
		assertEquals(name, person.getName());
	}

	@Test
	public void testPersistAndLoadEvent() {
		String name = "ECSE321 Tutorial";
		Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
		Event event = new Event();
		event.setName(name);
		event.setDate(date);
		event.setStartTime(startTime);
		event.setEndTime(endTime);
		eventRepository.save(event);

		event = null;

		event = eventRepository.findEventByName(name);

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
	}

}*/