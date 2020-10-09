package ca.mcgill.ecse321.onlineartgallerysystem.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.onlineartgallerysystem.model.Inventory;
import ca.mcgill.ecse321.onlineartgallerysystem.model.OnlineArtGallerySystem;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Posting;
import ca.mcgill.ecse321.onlineartgallerysystem.model.User;

@Repository
public interface PostingCrudRepository extends CrudRepository<Posting, Long>{
    
    List<Posting> findAll();
    
    Posting findPostingById(long id);
    
    void deleteAll();
}
