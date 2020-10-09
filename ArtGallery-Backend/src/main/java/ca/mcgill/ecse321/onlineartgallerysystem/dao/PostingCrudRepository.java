package ca.mcgill.ecse321.onlineartgallerysystem.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.onlineartgallerysystem.model.Inventory;
import ca.mcgill.ecse321.onlineartgallerysystem.model.OnlineArtGallerySystem;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Order;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Posting;
import ca.mcgill.ecse321.onlineartgallerysystem.model.User;

public interface PostingCrudRepository extends CrudRepository<Posting, Long>{
	
	
	Posting findUserById(long id);
	
	void deleteAll();


	

	
	
}