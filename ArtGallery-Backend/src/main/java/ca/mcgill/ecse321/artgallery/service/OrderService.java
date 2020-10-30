package ca.mcgill.ecse321.artgallery.service;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.artgallery.dao.PostingCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.UserRoleCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.OrderCrudRepository;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Client;
import ca.mcgill.ecse321.artgallery.model.ArtworkType;
import ca.mcgill.ecse321.artgallery.model.Order;
import ca.mcgill.ecse321.artgallery.model.Posting;
import ca.mcgill.ecse321.artgallery.model.Address;
import ca.mcgill.ecse321.artgallery.model.OrderStatus;
import ca.mcgill.ecse321.artgallery.service.exception.ArtworkException;
import ca.mcgill.ecse321.artgallery.service.exception.UserRoleException;
import ca.mcgill.ecse321.artgallery.service.exception.OrderException;



@Service
public class OrderService {
	
	@Autowired
	private OrderCrudRepository orderRepo;
	@Autowired
	private PostingCrudRepository postingRepo;
	@Autowired
	private UserRoleCrudRepository userRoleRepo;
	
	
	@Transactional
	public Order getOrder(long orderID) {
		
		Order order = orderRepo.findOrderById(orderID);
		return order;
	}
	
	@Transactional
	public Set<Order> getAllOrders() {
		
		Set<Order> orders = new HashSet<Order>();
		
		for (Order order : orderRepo.findAll()) {
			orders.add(order);
		}
		return orders;
	}
	
	@Transactional
	public Order createOrder(long userRoleID) {
				
		Client client = (Client) userRoleRepo.findUserRoleById(userRoleID);
		Set<Posting> items = new HashSet<Posting>();
		Order order = new Order();
		order.setClient((Client) userRoleRepo.findUserRoleById(userRoleID));
		//order.setId(value);
		//order.setInStorePickUp(inStorePickUp);
		order.setItems(items);
		
		//order.setOrderStatus(value);
		
		orderRepo.save(order);
		
		return order;		
	}
	
	@Transactional
	public Order deleteOrder(long orderID) {
		
		Order order = orderRepo.findOrderById(orderID);	
		orderRepo.deleteById(orderID);
		return order;
	}	
	
	@Transactional
	public Order addToOrder(long orderID, long postingID) {
		
		Order order = orderRepo.findOrderById(orderID);
		Posting posting = postingRepo.findPostingById(postingID);
		
		if (order == null || posting == null) {
			throw new OrderException("Order or posting does not exist");
		} else {
			Set<Posting> items = order.getItems();
			items.add(posting);
			order.setItems(items);
			orderRepo.save(order);
			return order;
		}
	}
	
	@Transactional
	public Order removeFromOrder(long orderID, long postingID) {
		
		Order order = orderRepo.findOrderById(orderID);
		Posting posting = postingRepo.findPostingById(postingID);
		
		if (order == null) {
			throw new OrderException("Order does not exist");
		} else {
			order.removeByPostingById(posting.getId());
			orderRepo.save(order);
			return order;
		}		
	}
	
	@Transactional
	public Order setInStorePickup(long orderID, boolean pickup) {
		
		Order order = orderRepo.findOrderById(orderID);
		
		if (order == null) {
			throw new OrderException("Order does not exist");
		} else {
			order.setInStorePickUp(pickup);
			orderRepo.save(order);
			return order;
		}	
	}
	
	@Transactional
	public Order setOrderStatus(long orderID, OrderStatus orderStatus) {
		
		Order order = orderRepo.findOrderById(orderID);
		
		if (order == null) {
			throw new OrderException("Order does not exist");
		} else {
			order.setOrderStatus(orderStatus);
			orderRepo.save(order);
			return order;
		}	
	}
	

}