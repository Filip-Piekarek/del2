package ca.mcgill.ecse321.artgallery.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.Artwork;

@Repository
public interface ArtworkCrudRepository extends CrudRepository<Artwork, Long>{
    
    Artwork findArtworkById(long id);
    
    void deleteAll();
}