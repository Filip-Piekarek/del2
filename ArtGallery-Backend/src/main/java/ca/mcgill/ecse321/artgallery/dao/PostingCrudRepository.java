package ca.mcgill.ecse321.artgallery.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.Inventory;
import ca.mcgill.ecse321.artgallery.model.OnlineArtGallerySystem;
import ca.mcgill.ecse321.artgallery.model.Posting;
import ca.mcgill.ecse321.artgallery.model.User;

@Repository
public interface PostingCrudRepository extends CrudRepository<Posting, Long>{
    
    List<Posting> findAll();
    
    Posting findPostingById(long id);
    
    void deleteAll();
}
