package ca.mcgill.ecse321.artgallery.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.artgallery.dao.ArtworkCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.OrderCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.PostingCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.UserRoleCrudRepository;
import ca.mcgill.ecse321.artgallery.dto.ArtworkDto;
import ca.mcgill.ecse321.artgallery.dto.OrderDto;
import ca.mcgill.ecse321.artgallery.dto.PostingDto;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Client;
import ca.mcgill.ecse321.artgallery.model.Order;
import ca.mcgill.ecse321.artgallery.model.Posting;
import ca.mcgill.ecse321.artgallery.service.exception.ArtworkException;
import ca.mcgill.ecse321.artgallery.service.exception.OrderException;
import ca.mcgill.ecse321.artgallery.service.exception.PostingException;
import ca.mcgill.ecse321.artgallery.service.exception.UserRoleException;

@Service
public class PostingService {

	@Autowired
	private UserRoleCrudRepository userRoleRepo;
	@Autowired
	private ArtworkCrudRepository artworkRepo;
	@Autowired
	private PostingCrudRepository postingRepo;
	@Autowired
	private OrderCrudRepository orderRepo;

	// Service methods for posting

	/**
	 * Returns all instances of postings inside the repository
	 * 
	 * @return
	 */
	public Set<PostingDto> getAllPostingsDto(){
		Set<PostingDto> allPosts = new HashSet<PostingDto>();
		for (Posting post : postingRepo.findAll()) {
			allPosts.add(convertPostingToDto(post));
		}
		return allPosts;

	}

	/**
	 * Method used for the class to return all objects from repository in
	 * the entity object.
	 * 
	 * @return
	 */
	public Set<Posting> getAllPostings(){
		Set<Posting> allPosts = new HashSet<Posting>();
		for (Posting post : postingRepo.findAll()) {
			allPosts.add(post);
		}
		return allPosts;

	}

	/**
	 * Returns the instance of posting found in the repository by id.
	 * 
	 * @param id
	 * @return
	 */
	public PostingDto getPosting(long id) {
		Posting post = postingRepo.findPostingById(id);
		if (post == null) {
			throw new PostingException("Posting not found");
		} else {
			return convertPostingToDto(post);
		}

	}

	/**
	 * Returns all postings associated to an artist.
	 * 
	 * @param artistId
	 * @return
	 */
	public Set<Posting> getAllPostingsByArtist(long artistId) {
		Artist artist = (Artist) userRoleRepo.findUserRoleById(artistId);
		if (artist == null) {
			throw new UserRoleException("Artist does not exist");
		} else {
			Set<Posting> postings = new HashSet<Posting>();
			for (Artwork art : artist.getArtworks()) {
				if (art.getPosting() != null) {
					postings.add(art.getPosting());
				}
			}
			return postings;

		}
	}

	/**
	 * Returns the posting corresponding to an artwork.
	 * 
	 * @param artistId
	 * @param artworkId
	 * @return
	 */
	public PostingDto getPostingOfArtwork(long artistId, long artworkId) {
		Artist artist = (Artist) userRoleRepo.findUserRoleById(artistId);
		Artwork artwork = artworkRepo.findArtworkById(artworkId);

		if (artist == null) {
			throw new UserRoleException("Artist does not exist");
		} else if (artwork == null) {
			throw new ArtworkException("Artwork does not exist");
		} else if (artist.getArtworkById(artworkId) == null) {
			throw new UserRoleException("Artist does not own artwork");
		} else if (artwork.getPosting() == null ) {
			throw new PostingException("Posting does not exist");
		} else {
			return convertPostingToDto(artwork.getPosting());
		}
	}

	/**
	 * Returns all postings added to a specific order.
	 * 
	 * @param clientId
	 * @param orderId
	 * @return
	 */
	public Set<PostingDto> getPostingsOfOrder(long clientId, long orderId){
		Client client = (Client) userRoleRepo.findUserRoleById(clientId);
		Order order = orderRepo.findOrderById(orderId);

		if (client == null) {
			throw new UserRoleException("Client does not exist");
		} else if (order == null) {
			throw new OrderException("Order does not exist");
		} else if (client.getOrderById(orderId) == null) {
			throw new UserRoleException("Client does not own Order");		
		} else {
			Set<PostingDto> items = new HashSet<PostingDto>();
			for (Posting item : order.getItems()) {
				items.add(convertPostingToDto(item));
			}
			return items;
		}
	}

	/**
	 * Create a posting object corresponding to an artwork.
	 * Default priority is 0.
	 * 
	 * @param item
	 * @param price
	 * @param visibility
	 * @param isOnline
	 * @param priority
	 * @return
	 */
	@Transactional
	public PostingDto createPosting(long artistId, long itemId, double price, boolean visibility, boolean isOnline) {

		Artist artist = (Artist) userRoleRepo.findUserRoleById(artistId);
		Artwork item = artworkRepo.findArtworkById(itemId);

		if (artist == null) {
			throw new UserRoleException("Artist does not exist");
		} else if (item == null) {
			throw new ArtworkException("Artwork does not exist");
		} else if (artist.getArtworkById(item.getId()) == null) {
			throw new UserRoleException("Artist does not own artwork");
		}  else {
			// Create posting
			Posting posting = new Posting();
			posting.setItem(item);
			posting.setPrice(price);
			posting.setVisibility(visibility);
			posting.setIsOnline(isOnline);
			posting.setPriority(0);


			//Save in repository
			postingRepo.save(posting);

			return convertPostingToDto(posting);
		}

	}

	/**
	 * Remove a posting from an artwork.
	 * Also removes posting from repository.
	 * 
	 * @param postId
	 * @param itemId
	 * @param artistId
	 * @return
	 */
	@Transactional
	public boolean deletePosting(long artistId, long itemId, long postId) {

		Posting posting = postingRepo.findPostingById(postId);
		Artwork item = artworkRepo.findArtworkById(itemId);
		Artist artist = (Artist) userRoleRepo.findUserRoleById(artistId);


		if (artist == null) {
			throw new UserRoleException("Artist does not exist");
		} else if (item == null) {
			throw new ArtworkException("Artwork does not exist");
		} else if (posting == null) {
			throw new PostingException("Posting does not exist");
		} else if (artist.getArtworkById(item.getId())== null) {
			throw new UserRoleException("Artist does not own artwork");
		} else if (item.getPosting() == null || item.getPosting().getId() != posting.getId()) {
			throw new PostingException("Posting does not correspond to this artwork");
		} else {

			if (posting.getOrder() != null) {
				Order order = orderRepo.findOrderById(posting.getOrder().getId());
				for (Posting post : order.getItems()) {
					if (post.getId() == postId) {
						order.getItems().remove(post);
					}
				}

				orderRepo.save(order);
			}

			item.setPosting(null);
			artworkRepo.save(item);

			postingRepo.deleteById(postId);

			return true;
		}

	}

	/**
	 * Edit the price of the artwork.
	 * 
	 * @param id
	 * @param nPrice
	 * @return
	 */
	@Transactional
	public PostingDto editPostingPrice(long artistId, long postingId, double nPrice) {
		Artist artist = (Artist) userRoleRepo.findUserRoleById(artistId);
		Posting posting = postingRepo.findPostingById(postingId);

		if (artist == null) {
			throw new UserRoleException("Artist does not exist");
		} else if (posting == null) {
			throw new PostingException("Posting does not exist");
		} else if (artist.getArtworkById(posting.getItem().getId())== null) {
			throw new UserRoleException("Artist does not own posting");
		}  else {
			posting.setPrice(nPrice);

			postingRepo.save(posting);

			return convertPostingToDto(posting);
		}


	}

	/**
	 * Remove the posting from the display of the postings.
	 * Clients cannot buy postings that are taken down.
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public PostingDto takeDownPosting(long artistId, long id) {
		Artist artist = (Artist) userRoleRepo.findUserRoleById(artistId);
		Posting posting = postingRepo.findPostingById(id);

		if (artist == null) {
			throw new UserRoleException("Artist does not exist");
		} else if (posting == null) {
			throw new PostingException("Posting does not exist");
		} else if (artist.getArtworkById(posting.getItem().getId()) == null) {
			throw new UserRoleException("Artist does not own posting");
		} else {
			posting.setVisibility(false);

			postingRepo.save(posting);

			return convertPostingToDto(posting);
		}
	}

	/**
	 * Make posting visible on the display of all postings
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public PostingDto putUpPosting(long artistId, long postingId) {

		Artist artist = (Artist) userRoleRepo.findUserRoleById(artistId);
		Posting posting = postingRepo.findPostingById(postingId);

		if (artist == null) {
			throw new UserRoleException("Artist does not exist");
		} else if (posting == null) {
			throw new PostingException("Posting does not exist");
		} else if (artist.getArtworkById(posting.getItem().getId()) == null) {
			throw new UserRoleException("Artist does not own posting");
		} else {
			posting.setVisibility(true);

			postingRepo.save(posting);

			return convertPostingToDto(posting);
		}
	}

	/**
	 * If artists want to promote their postings by displaying
	 * it in front of the list, they can buy priority.
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public PostingDto promotePosting(long artistId, long postingId) {

		Posting posting = postingRepo.findPostingById(postingId);
		Artist artist = (Artist) userRoleRepo.findUserRoleById(artistId);

		if (artist == null) {
			throw new UserRoleException("Artist does not exist");
		} else if (posting == null) {
			throw new PostingException("Posting does not exist");
		} else if (artist.getArtworkById(posting.getItem().getId()) == null) {
			throw new UserRoleException("Artist does not own posting");
		} else {

			int maxP = 0;

			Set<Posting> currentPostings = new HashSet<Posting>();
			currentPostings = getAllPostings();

			for (Posting post : currentPostings) {
				if (post.getPriority()>maxP) {
					maxP = post.getPriority();
				}
			}

			posting.setPriority(maxP+1);

			postingRepo.save(posting);

			return convertPostingToDto(posting);
		}
	}

	/**
	 * Converts a Posting entity object to a Posting data transfer object.
	 * 
	 * @param posting
	 * @return
	 */
	public PostingDto convertPostingToDto(Posting posting) {

		PostingDto postingDto = new PostingDto();

		postingDto.setId(posting.getId());
		postingDto.setItem(convertArtworkToDto(posting.getItem()));
		postingDto.setPrice(posting.getPrice());
		postingDto.setVisibility(posting.isVisibility());
		postingDto.setPriority(posting.getPriority());

		if (posting.getOrder() != null) {
			postingDto.setOrder(convertOrderToDto(posting.getOrder()));
		}

		return postingDto;

	}

	public ArtworkDto convertArtworkToDto(Artwork art) {
		// to implement in artwork service class
		return null;
	}

	public OrderDto convertOrderToDto(Order order) {
		// to implement in order service class
		return null;
	}

}
