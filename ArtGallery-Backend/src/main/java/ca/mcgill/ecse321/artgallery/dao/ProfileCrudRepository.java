package ca.mcgill.ecse321.artgallery.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.Inventory;
import ca.mcgill.ecse321.artgallery.model.OnlineArtGallerySystem;
import ca.mcgill.ecse321.artgallery.model.Profile;
import ca.mcgill.ecse321.artgallery.model.User;

@Repository
public interface ProfileCrudRepository extends CrudRepository<Profile, String>{
    
    Profile findProfileByUserName(String name);
    
    Profile findProfileById(long id);
    
    void deleteAll();
}
