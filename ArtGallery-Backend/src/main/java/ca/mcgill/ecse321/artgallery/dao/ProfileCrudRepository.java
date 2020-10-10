package ca.mcgill.ecse321.artgallery.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.Profile;

@Repository
public interface ProfileCrudRepository extends CrudRepository<Profile, String>{

    Profile findProfileByUsername(String username);
    
    void deleteAll();
}
