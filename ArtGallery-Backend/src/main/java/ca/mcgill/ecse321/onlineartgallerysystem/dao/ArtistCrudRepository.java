package ca.mcgill.ecse321.onlineartgallerysystem.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.onlineartgallerysystem.model.Artist;
import ca.mcgill.ecse321.onlineartgallerysystem.model.User;

@Repository
public interface ArtistCrudRepository extends CrudRepository<Artist, Long>{
    
    Artist findArtistByUserName(String name);
    
    Artist findArtworkById(long id);
    
    void deleteAll();
}
