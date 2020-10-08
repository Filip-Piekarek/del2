package ca.mcgill.ecse321.onlineartgallerysystem.dao;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.onlineartgallerysystem.model.OnlineArtGallerySystem;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Profile;
import ca.mcgill.ecse321.onlineartgallerysystem.model.User;

@Repository
public class ProfileRepository {
	
	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public Profile createProfile(OnlineArtGallerySystem system, String username, boolean artist, String phone, String email, User user) {
		
		Profile profile = new Profile();
		profile.setEmailAddress(email);
		profile.setIsArtistProfile(artist);
		profile.setPhoneNumber(phone);
		profile.setSystem(system);
		profile.setUser(user);
		profile.setUsername(username);
		entityManager.persist(profile);
		return profile;
	}
	
	@Transactional
	public Profile getProfile(User user) {
		
		Profile profile = entityManager.find(Profile.class, user);
		return profile;
	}
}	
