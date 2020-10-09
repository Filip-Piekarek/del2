package ca.mcgill.ecse321.onlineartgallerysystem.dao;

import java.sql.Date;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.onlineartgallerysystem.model.Artist;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Artwork;
import ca.mcgill.ecse321.onlineartgallerysystem.model.ArtworkType;
import ca.mcgill.ecse321.onlineartgallerysystem.model.OnlineArtGallerySystem;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Posting;

@Repository
public class ArtworkRepository {

	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public Artwork createArtwork(String name, String description, long artId, ArtworkType type, Artist creator, Date date, OnlineArtGallerySystem system, Posting posting) {
		Artwork art = new Artwork();
		art.setArtworkType(type);
		art.setCreator(creator);
		art.setDate(date);
		art.setId(artId);
		art.setDescription(description);
		art.setName(name);
		art.setPosting(posting);
		art.setSystem(system);
		entityManager.persist(art);
		return art;
	}
	
	@Transactional
	public Artwork getArtwork(long artId) {
		Artwork art = entityManager.find(Artwork.class, artId);
		return art;
	}
}
