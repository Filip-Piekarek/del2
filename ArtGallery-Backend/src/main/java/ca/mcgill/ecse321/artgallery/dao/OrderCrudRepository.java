package ca.mcgill.ecse321.artgallery.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.Inventory;
import ca.mcgill.ecse321.artgallery.model.OnlineArtGallerySystem;
import ca.mcgill.ecse321.artgallery.model.Order;
import ca.mcgill.ecse321.artgallery.model.User;

@Repository
public interface OrderCrudRepository extends CrudRepository<Order, Long>{
    
    Order findOrderByUserName(String name);
    
    Order findOrderById(long id);
    
    void deleteAll();
}
