package ca.mcgill.ecse321.onlineartgallerysystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.onlineartgallerysystem.dao.ArtistCrudRepository;
import ca.mcgill.ecse321.onlineartgallerysystem.dao.ArtworkCrudRepository;
import ca.mcgill.ecse321.onlineartgallerysystem.dao.InventoryCrudRepository;
import ca.mcgill.ecse321.onlineartgallerysystem.dao.OrderCrudRepository;
import ca.mcgill.ecse321.onlineartgallerysystem.dao.PostingCrudRepository;
import ca.mcgill.ecse321.onlineartgallerysystem.dao.ProfileCrudRepository;
import ca.mcgill.ecse321.onlineartgallerysystem.dao.UserCrudRepository;
import ca.mcgill.ecse321.onlineartgallerysystem.model.User;

@Service
public class TestArtGalleryService {

	@Autowired
	private ArtistCrudRepository artistRepo;
	@Autowired
	private ArtworkCrudRepository artworkRepo;
	@Autowired
	private OrderCrudRepository orderRepo;
	@Autowired
	private PostingCrudRepository postingRepo;
	@Autowired
	private ProfileCrudRepository profileRepo;
	@Autowired
	private UserCrudRepository userRepo;
	@Autowired
	private InventoryCrudRepository inventoryRepo;

	@Transactional
	public User createUser(String name) {
		
		User user = new User();
		user.setName(name);
		userRepo.save(user);
		return user;
	}
	
	@Transactional
	public User getUser(String name) {
		
		User user = userRepo.findUserByUserName(name);
		return user;
	}
	
	@Transactional
	public List<User> getAllUser() {
		
		return toList(userRepo.findAll());
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
