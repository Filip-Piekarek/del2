package ca.mcgill.ecse321.onlineartgallerysystem.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.onlineartgallerysystem.model.Inventory;
import ca.mcgill.ecse321.onlineartgallerysystem.model.OnlineArtGallerySystem;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Order;
import ca.mcgill.ecse321.onlineartgallerysystem.model.User;

@Repository
public interface OrderCrudRepository extends CrudRepository<Order, Long>{
    
    Order findOrderByUserName(String name);
    
    Order findOrderById(long id);
    
    void deleteAll();
}
