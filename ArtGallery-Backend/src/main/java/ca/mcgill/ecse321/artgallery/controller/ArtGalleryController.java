package ca.mcgill.ecse321.artgallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.artgallery.service.ArtistService;
import ca.mcgill.ecse321.artgallery.service.ArtworkService;
import ca.mcgill.ecse321.artgallery.service.ClientService;
import ca.mcgill.ecse321.artgallery.service.OrderService;
import ca.mcgill.ecse321.artgallery.service.ProfileService;
import ca.mcgill.ecse321.artgallery.service.UserRoleService;
import ca.mcgill.ecse321.artgallery.service.UserService;

@CrossOrigin(origins = "*")
@RestController
public class ArtGalleryController {

	@Autowired
	private ArtworkService artService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ArtistService artistService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private UserRoleService userRoleService;
}
