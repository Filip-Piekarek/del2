package ca.mcgill.ecse321.artgallery.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Client;
import ca.mcgill.ecse321.artgallery.model.Inventory;
import ca.mcgill.ecse321.artgallery.model.OnlineArtGallerySystem;
import ca.mcgill.ecse321.artgallery.model.User;

public interface ClientCrudRepository extends CrudRepository<Client, Long>{
	
	Client findUserByUserName(String name);
	
	Client findUserById(long id);
	
	void deleteAll();


	

	
	
}