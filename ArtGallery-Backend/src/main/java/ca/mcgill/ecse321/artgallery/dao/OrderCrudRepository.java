package ca.mcgill.ecse321.artgallery.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.artgallery.model.Order;

@Repository
public interface OrderCrudRepository extends CrudRepository<Order, Long>{
    
    
    Order findOrderById(long id);
    
    void deleteAll();
}
