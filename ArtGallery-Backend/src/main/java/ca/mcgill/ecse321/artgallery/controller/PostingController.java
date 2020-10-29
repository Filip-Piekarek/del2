package ca.mcgill.ecse321.artgallery.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.artgallery.dto.PostingDto;
import ca.mcgill.ecse321.artgallery.model.Posting;
import ca.mcgill.ecse321.artgallery.service.PostingService;

@CrossOrigin(origins = "*")
@RestController
public class PostingController {

	@Autowired
	private PostingService postingService;

	@GetMapping(value = { "/postings", "/postings/" })
	public Set<PostingDto> getAllPostings(){
		return postingService.getAllPostingsDto();
	}
	
	@GetMapping(value = { "/postings/{postingId}", "/postings/{postingId}/"})
	public PostingDto getPosting(@PathVariable("postingId") long postingId) {
		return postingService.getPosting(postingId);
	}
	
	@GetMapping(value = { "/artists/{artistId}/postings", "/artists/{artistId}/postings/"})
	public Set<PostingDto> getAllPostingsOfArtist(@PathVariable("artistId") long artistId){
		Set<PostingDto> postings = new HashSet<PostingDto>();
		PostingDto posting = null;
		for (Posting post : postingService.getAllPostingsByArtist(artistId)) {
			posting = postingService.convertPostingToDto(post);
			postings.add(posting);
		}
		return postings;
	}
	
	@GetMapping(value = { "/{artistId}/{artworkId}/posting", "/{artistId}/{artworkId}/posting/"})
	public PostingDto getPostingOfArtwork(@PathVariable("artistId") long artistId, @PathVariable("artworkId") long artworkId) {
		return postingService.getPostingOfArtwork(artistId, artistId);
	}
	
	@GetMapping(value = {"/{clientId}/orders/{orderId}/items", "/{clientId}/orders/{orderId}/items/"})
	public Set<PostingDto> getPostingsOfOrder(@PathVariable("clientId") long clientId, @PathVariable("orderId") long orderId){
		return postingService.getPostingsOfOrder(clientId, orderId);
	}

	@PostMapping(value = {"/postings/{postingId}", "/postings/{postingId}/"})
	public PostingDto createPosting(long artistId, @PathVariable("postingId") long itemId, double price, boolean visibility, boolean isOnline)
			throws IllegalArgumentException {
		return postingService.createPosting(artistId, itemId, price, visibility, isOnline);
	}
	
	@GetMapping(value = {"/{artistId}/artworks/{artworkId}/{postingId}", "/{artistId}/artworks/{artworkId}/{postingId}/"})
	public boolean deletePosting(@PathVariable("artistId") long artistId, @PathVariable("artworkId") long artworkId, @PathVariable("postingId") long postingId) {
		return postingService.deletePosting(artistId, artworkId, postingId);
	}
	
	@GetMapping(value = {"/{artistId}/postings/{postingId}/edit/price", "/{artistId}/postings/{postingId}/edit/price/"})
	public PostingDto editPricing(@PathVariable("/artistId") long artistId, @PathVariable("postingId") long postingId, @RequestParam double nPrice) {
		return postingService.editPostingPrice(artistId, postingId, nPrice);
	}
	
	@GetMapping(value = {"/{artistId}/postings/{postingId}/edit/visibility", "/{artistId}/postings/{postingId}/edit/visibility/"})
	public PostingDto editVisibility(@PathVariable("/artistId") long artistId, @PathVariable("postingId") long postingId, @RequestParam boolean visibility) {
		if (visibility) {
			return postingService.putUpPosting(artistId, postingId);
		} else {
			return postingService.takeDownPosting(artistId, postingId);
		}
	}
	
	
	@GetMapping(value = {"/{artistId}/postings/{postingId}/edit/promote", "/{artistId}/postings/{postingId}/edit/promote/"})
	public PostingDto promotePosting(@PathVariable("/artistId") long artistId, @PathVariable("postingId") long postingId) {
		return postingService.promotePosting(artistId, postingId);
	}
}
