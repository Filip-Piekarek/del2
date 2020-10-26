package ca.mcgill.ecse321.artgallery.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.artgallery.dao.ArtworkCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.UserRoleCrudRepository;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.ArtworkType;
import ca.mcgill.ecse321.artgallery.service.exception.UserRoleException;

@Service
public class ArtworkService {
	@Autowired
	private ArtworkCrudRepository artworkRepo;
	@Autowired
	private UserRoleCrudRepository userRoleRepo;
	
	/**
	 * Creates and ads an ArtWork if the user is an Artist.
	 * 
	 * @param artist
	 * @param name
	 * @param description
	 * @param date
	 * @param type
	 * @param UserRoleid
	 * @return
	 */
	@Transactional
	public Artwork createArtwork(Artist artist, String name, String description, Date date, ArtworkType type,long userRoleid) {
		//Will create and add artwork to a profile if the profile is of an artist
		//Create artwork
		artist = (Artist) userRoleRepo.findUserRoleById(userRoleid);
		if (artist == null) {
			 throw new UserRoleException("Artist does not exist");
		 }
		else {
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
			
	}

}
