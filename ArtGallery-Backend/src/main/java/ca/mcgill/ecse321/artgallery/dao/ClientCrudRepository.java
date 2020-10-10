package ca.mcgill.ecse321.artgallery.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import ca.mcgill.ecse321.artgallery.model.Client;

@Repository
public interface ClientCrudRepository extends CrudRepository<Client, Long>{
	
	Client findClientById(long id);
	
	void deleteAll();


	

	
	
}