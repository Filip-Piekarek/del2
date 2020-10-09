package ca.mcgill.ecse321.artgallery.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.User;

@Repository
public interface ArtistCrudRepository extends CrudRepository<Artist, Long>{
    
    Artist findArtistByUserName(String name);
    
    Artist findArtworkById(long id);
    
    void deleteAll();
}
