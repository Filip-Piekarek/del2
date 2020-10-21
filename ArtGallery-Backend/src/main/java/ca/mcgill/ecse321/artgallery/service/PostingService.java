package ca.mcgill.ecse321.artgallery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.artgallery.dao.ArtworkCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.OrderCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.PostingCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.ProfileCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.UserCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.UserRoleCrudRepository;

@Service
public class PostingService {
	
	@Autowired
	private UserCrudRepository userRepo;
	@Autowired 
	private UserRoleCrudRepository userRoleRepo;
	@Autowired
	private ProfileCrudRepository profileRepo;
	@Autowired
	private ArtworkCrudRepository artworkRepo;
	@Autowired
	private PostingCrudRepository postingRepo;
	@Autowired
	private OrderCrudRepository orderRepo;
	
}