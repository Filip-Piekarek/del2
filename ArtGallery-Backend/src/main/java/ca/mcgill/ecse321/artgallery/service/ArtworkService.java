package ca.mcgill.ecse321.artgallery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.artgallery.dao.ArtworkCrudRepository;

@Service
public class ArtworkService {
	@Autowired
	private ArtworkCrudRepository artworkRepo;

}
