package ca.mcgill.ecse321.artgallery.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.User;

@Repository
public interface UserCrudRepository extends CrudRepository<User, Long>{
    
    User findUserById(long id);
    
    void deleteAll();
}
