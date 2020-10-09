package ca.mcgill.ecse321.onlineartgallerysystem.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.onlineartgallerysystem.model.Inventory;
import ca.mcgill.ecse321.onlineartgallerysystem.model.OnlineArtGallerySystem;
import ca.mcgill.ecse321.onlineartgallerysystem.model.User;

public interface InventoryCrudRepository extends CrudRepository<Inventory, Long>{
	
	Inventory findUserByUserName(String name);
	
	Inventory findUserById(long id);
	
	void deleteAll();


	

	
	
}