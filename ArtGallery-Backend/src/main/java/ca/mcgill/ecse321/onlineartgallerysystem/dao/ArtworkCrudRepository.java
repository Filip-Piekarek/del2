package ca.mcgill.ecse321.onlineartgallerysystem.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.onlineartgallerysystem.model.Artwork;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Inventory;
import ca.mcgill.ecse321.onlineartgallerysystem.model.OnlineArtGallerySystem;
import ca.mcgill.ecse321.onlineartgallerysystem.model.User;

public interface ArtworkCrudRepository extends CrudRepository<Artwork, Long>{
	
	Artwork findUserByUserName(String name);
	
	Artwork findUserById(long id);
	
	void deleteAll();


	

	
	
}