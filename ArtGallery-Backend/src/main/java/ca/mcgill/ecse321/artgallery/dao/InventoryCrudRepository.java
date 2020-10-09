package ca.mcgill.ecse321.artgallery.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.Inventory;
import ca.mcgill.ecse321.artgallery.model.OnlineArtGallerySystem;
import ca.mcgill.ecse321.artgallery.model.User;

@Repository
public interface InventoryCrudRepository extends CrudRepository<Inventory, Long>{
    
    Inventory findInventoryByUserName(String name);
    
    Inventory findUserById(long id);
    
    void deleteAll();
}
