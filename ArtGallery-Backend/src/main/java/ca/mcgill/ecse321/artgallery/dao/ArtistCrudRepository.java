package ca.mcgill.ecse321.artgallery.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.Artist;

@Repository
public interface ArtistCrudRepository extends CrudRepository<Artist, Long>{
    
    Artist findArtistById(long id);
    
    void deleteAll();
}
