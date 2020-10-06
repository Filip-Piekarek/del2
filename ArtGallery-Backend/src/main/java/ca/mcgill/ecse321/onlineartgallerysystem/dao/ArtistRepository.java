package ca.mcgill.ecse321.onlineartgallerysystem.dao;

import java.util.Set;

import javax.persistence.EntityManager;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.onlineartgallerysystem.model.Artist;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Artwork;

@Repository
public class ArtistRepository {
	
	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public Artist createArtist(Set<Artwork> artworkss, String biography) {
		
		Artist artist = new Artist();
		artist.setArtworks(artworkss);
		artist.setBiography(biography);
		entityManager.persist(artist);
		return artist;
	}
	
	public Artist getArtist(User user) {
		
		Artist artist = entityManager.find(Artist.class, user);
		return artist;
	}
}
