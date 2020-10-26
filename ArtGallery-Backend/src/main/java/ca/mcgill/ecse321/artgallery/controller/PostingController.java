package ca.mcgill.ecse321.artgallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.artgallery.service.PostingService;

@CrossOrigin(origins = "*")
@RestController
public class PostingController {
	
	@Autowired
	private PostingService postingService;

}
