package ca.mcgill.ecse321.onlineartgallerysystem.dao;

import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.onlineartgallerysystem.model.Client;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Order;
import ca.mcgill.ecse321.onlineartgallerysystem.model.OrderStatus;
import ca.mcgill.ecse321.onlineartgallerysystem.model.Posting;

@Repository
public class OrderRepository {
	
	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public Order createOrder(Client client, Set<Posting> items, boolean pickup, Integer id, OrderStatus status) {
		Order order = new Order();
		order.setClient(client);
		order.setId(id);
		order.setInStorePickUp(pickup);
		order.setItems(items);
		order.setOrderStatus(status);
		entityManager.persist(order);
		return order;
	}
	
	@Transactional
	public Order getOrder(Integer id) {
		Order order = entityManager.find(Order.class, id);
		return order;
	}
}
