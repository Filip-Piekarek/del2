package ca.mcgill.ecse321.artgallery.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.UserRole;

@Repository
public interface UserRoleCrudRepository extends CrudRepository<UserRole, Long> {
	
	UserRole findUserRoleById(long id);
	
	void deleteAll();
}
