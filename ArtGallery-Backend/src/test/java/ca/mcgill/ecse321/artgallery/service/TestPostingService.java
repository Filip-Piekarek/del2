package ca.mcgill.ecse321.artgallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.mockito.invocation.InvocationOnMock;

import ca.mcgill.ecse321.artgallery.dao.ArtworkCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.PostingCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.ProfileCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.UserCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.UserRoleCrudRepository;
import ca.mcgill.ecse321.artgallery.dto.PostingDto;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.ArtworkType;
import ca.mcgill.ecse321.artgallery.model.Posting;
import ca.mcgill.ecse321.artgallery.model.Profile;
import ca.mcgill.ecse321.artgallery.model.User;
import ca.mcgill.ecse321.artgallery.service.exception.ArtworkException;
import ca.mcgill.ecse321.artgallery.service.exception.PostingException;
import ca.mcgill.ecse321.artgallery.service.exception.UserRoleException;


@ExtendWith(MockitoExtension.class)
public class TestPostingService {

	@Mock
	private PostingCrudRepository postingDao;
	@Mock
	private UserCrudRepository userDao;
	@Mock
	private UserRoleCrudRepository userRoleDao;
	@Mock
	private ProfileCrudRepository profileDao;
	@Mock
	private ArtworkCrudRepository artworkDao;

	@InjectMocks
	private PostingService postingService;

	// User parameters
	private static final long USER_KEY = 1;
	private static final String USER_NAME = "Morgane";
	private User USER = new User();

	// Profile parameters
	private static final String PROFILE_USERNAME = "mochi";
	private static final boolean ARTIST_PROFILE = true;
	private static final String PHONE_NUMBER = "514-234-1243";
	private static final String EMAIL_ADDRESS = "morgane@gmail.com";
	private static final String PASSWORD = "thIs8IsaValidPass@";
	private Profile PROFILE = new Profile();

	// UserRole parameters
	private static final long USERROLE_KEY = 2;
	private static final String BIO = "hello world";
	Artist ARTIST = new Artist();

	// Artwork parameters
	private static final long ARTWORK_KEY = 4;
	private static final String ARTWORK_NAME = "Lila";
	private static final String DESCRIPTION = "painting";
	private static final ArtworkType TYPE = ArtworkType.ACRYLIC_PAINTING;
	private static final Date DATE = Date.valueOf(LocalDate.of(2020, Month.APRIL, 20));
	Artwork ARTWORK = new Artwork();
	
	// Second artwork
	private static final long ARTWORK_KEY2 = 63;
	private static final String ARTWORK_NAME2 = "Rouge";
	private static final String DESCRIPTION2 = "Sculpture";
	private static final ArtworkType TYPE2 = ArtworkType.METAL_SCULPTURE;
	private static final Date DATE2 = Date.valueOf(LocalDate.of(2020, Month.NOVEMBER, 2));
	Artwork ARTWORK2 = new Artwork();

	// Posting parameters
	private static final long POSTING_KEY = 0;
	private static final double PRICE = 5000;
	private static final boolean VISIBILITY = true;
	private static final boolean IS_ONLINE = true;
	private static final int PRIORITY = 0;
	Posting POSTING = new Posting();

	// Invalid parameters
	private static final long INVALID_POSTING_KEY = 21;
	private static final long INVALID_ARTIST_KEY = 23;
	private static final long INVALID_ARTWORK_KEY = 52;


	// Dummy artist with no artwork
	// User2 parameters
	private static final long USER_KEY2 = 9;
	private static final String USER_NAME2 = "Antoine";
	private User USER2 = new User();

	// Profile parameters
	private static final String PROFILE_USERNAME2 = "antonio";
	private static final boolean ARTIST_PROFILE2 = true;
	private static final String PHONE_NUMBER2 = "514-543-1343";
	private static final String EMAIL_ADDRESS2 = "antoine@gmail.com";
	private static final String PASSWORD2 = "thIs5IsaValidPass#";
	private Profile PROFILE2 = new Profile();

	// UserRole parameters
	private static final long USERROLE_KEY2 = 12;
	private static final String BIO2 = "hello world!";
	Artist ARTIST2 = new Artist();


	private void generalSetUp() {

		// user
		USER.setId(USER_KEY);
		USER.setName(USER_NAME);

		// profile
		PROFILE.setUsername(PROFILE_USERNAME);
		PROFILE.setEmailAddress(EMAIL_ADDRESS);
		PROFILE.setIsArtistProfile(ARTIST_PROFILE);
		PROFILE.setPassword(PASSWORD);
		PROFILE.setPhoneNumber(PHONE_NUMBER);
		PROFILE.setUser(USER);

		USER.setProfile(PROFILE);		


		Set<Artwork> artworks = new HashSet<Artwork>();
		ARTIST.setId(USERROLE_KEY);
		ARTIST.setBiography(BIO);
		ARTIST.setArtworks(artworks);
		ARTIST.setUser(USER);



		ARTWORK.setArtworkType(TYPE);
		ARTWORK.setDate(DATE);
		ARTWORK.setDescription(DESCRIPTION);
		ARTWORK.setName(ARTWORK_NAME);
		ARTWORK.setId(ARTWORK_KEY);
		ARTWORK.setCreator(ARTIST);
		ARTIST.addArtwork(ARTWORK);
		
		ARTWORK2.setArtworkType(TYPE2);
		ARTWORK2.setDate(DATE2);
		ARTWORK2.setDescription(DESCRIPTION2);
		ARTWORK2.setName(ARTWORK_NAME2);
		ARTWORK2.setId(ARTWORK_KEY2);
		ARTWORK2.setCreator(ARTIST);
		ARTWORK2.setPosting(null);
		ARTIST.addArtwork(ARTWORK2);


		POSTING.setId(POSTING_KEY);
		POSTING.setIsOnline(IS_ONLINE);
		POSTING.setPrice(PRICE);
		POSTING.setPriority(PRIORITY);
		POSTING.setVisibility(VISIBILITY);
		POSTING.setItem(ARTWORK);
		ARTWORK.setPosting(POSTING);


	}

	private void dummyArtistSetUp() {
		// user
		USER2.setId(USER_KEY2);
		USER2.setName(USER_NAME2);

		// profile
		PROFILE2.setUsername(PROFILE_USERNAME2);
		PROFILE2.setEmailAddress(EMAIL_ADDRESS2);
		PROFILE2.setIsArtistProfile(ARTIST_PROFILE2);
		PROFILE2.setPassword(PASSWORD2);
		PROFILE2.setPhoneNumber(PHONE_NUMBER2);
		PROFILE2.setUser(USER2);

		USER2.setProfile(PROFILE2);		

		Set<Artwork> artworks = new HashSet<Artwork>();
		ARTIST2.setId(USERROLE_KEY2);
		ARTIST2.setBiography(BIO2);
		ARTIST2.setArtworks(artworks);
		ARTIST2.setUser(USER2);
	}

	@BeforeEach
	public void setMockOutput() {
		MockitoAnnotations.initMocks(this);

		lenient().when(userRoleDao.findUserRoleById(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(USERROLE_KEY)) {

				generalSetUp();

				return ARTIST;

			} else if (invocation.getArgument(0).equals(USERROLE_KEY2)){

				dummyArtistSetUp();

				return ARTIST2;

			} else {
				return null;
			}

		});

		lenient().when(artworkDao.findArtworkById(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(ARTWORK_KEY)) {

				generalSetUp();

				return ARTWORK;

			} else if (invocation.getArgument(0).equals(ARTWORK_KEY2)){
				generalSetUp();
				
				return ARTWORK2;
			} else {
				return null;
			}
		});

		lenient().when(postingDao.findPostingById(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(POSTING_KEY)) {

				generalSetUp();

				return POSTING;

			} else {
				return null;
			}
		});

		lenient().when(postingService.getAllPostingsDto()).thenAnswer( (InvocationOnMock invocation) -> {

			generalSetUp();
			List<Posting> postings = new ArrayList<Posting>();
			postings.add(POSTING);
			return postings;

		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};

		lenient().when(postingDao.save(any(Posting.class))).thenAnswer(returnParameterAsAnswer);
	}


	@Test
	public void testCreatePosting() {

		// POSTING CREATION

		PostingDto post = null;

		try {
			post = postingService.createPosting(USERROLE_KEY, ARTWORK_KEY, 5000, true, true);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(post);
		assertEquals(PRICE, post.getPrice());

	}

	@Test
	public void testCreatePostingWihtoutValidArtistId() {
		PostingDto post = null;
		
		try {
			post = postingService.createPosting(INVALID_ARTIST_KEY, ARTWORK_KEY, PRICE, VISIBILITY, IS_ONLINE);
		} catch (UserRoleException e) {
			assertEquals("Artist does not exist", e.getMessage());
		}
		
		assertNull(post);
	}
	
	@Test
	public void testCreatePostingWithoutCorrectOwner() {
		PostingDto post = null;
		
		try {
			post = postingService.createPosting(USERROLE_KEY2, ARTWORK_KEY, PRICE, VISIBILITY, IS_ONLINE);
		} catch (UserRoleException e) {
			assertEquals("Artist does not own artwork", e.getMessage());
		}
		assertNull(post);
	}
	
	@Test
	public void testCreatePostingWithNullArtwork() {
		PostingDto post = null;
		
		try {
			post = postingService.createPosting(USERROLE_KEY, INVALID_ARTWORK_KEY, PRICE, VISIBILITY, IS_ONLINE);
		} catch (ArtworkException e) {
			assertEquals("Artwork does not exist", e.getMessage());
		}
		
		assertNull(post);
	}
	
	@Test
	public void testDeleteValidPosting() {
		try {
			assertTrue(postingService.deletePosting(USERROLE_KEY, ARTWORK_KEY, POSTING_KEY));
		} catch (Exception e) {
			fail();
		}
		
	}
	
	@Test
	public void testDeleteNonExistantPosting() {
		boolean delete = false;
		try {
			delete = postingService.deletePosting(USERROLE_KEY, ARTWORK_KEY, INVALID_POSTING_KEY);
		} catch (PostingException e) {
			assertEquals("Posting does not exist", e.getMessage());
		}
		assertFalse(delete);
	}
	
	@Test
	public void testDeleteUserNotAllowed() {

		boolean delete = false;
		try {
			delete = postingService.deletePosting(USERROLE_KEY2, ARTWORK_KEY, POSTING_KEY);
		} catch (UserRoleException e) {
			assertEquals("Artist does not own artwork", e.getMessage());
		}
		assertFalse(delete);
	}
	
	@Test
	public void testDeleteUserRoleDoesNotExist() {

		boolean delete = false;
		try {
			delete = postingService.deletePosting(INVALID_ARTIST_KEY, ARTWORK_KEY, POSTING_KEY);
		} catch (UserRoleException e) {
			assertEquals("Artist does not exist", e.getMessage());
		}
		assertFalse(delete);
	
	}
	
	@Test
	public void testDeleteArtworkDoesNotExist() {

		boolean delete = false;
		try {
			delete = postingService.deletePosting(USERROLE_KEY, INVALID_ARTWORK_KEY, POSTING_KEY);
		} catch (ArtworkException e) {
			assertEquals("Artwork does not exist", e.getMessage());
		}
		assertFalse(delete);
	
	}
	
	@Test
	public void testDeleteArtworkDoesNotCorrespondToPosting() {

		boolean delete = false;
		try {
			delete = postingService.deletePosting(USERROLE_KEY, ARTWORK_KEY2, POSTING_KEY);
		} catch (PostingException e) {
			assertEquals("Posting does not correspond to this artwork", e.getMessage());
		}
		assertFalse(delete);
	
	}
	
	@Test
	public void testGetAllPostings() {
		Set<Posting> allPostings = new HashSet<Posting>();
		
		try {
			allPostings = postingService.getAllPostings();
		} catch (Exception e) {
			fail();
		}
		
		assertFalse(allPostings.isEmpty());
		assertTrue(allPostings.contains(POSTING));
	}

//	@Test
//	public void testGetValidPosting() {
//
//		PostingDto post = null;
//
//		try {
//			post = postingService.getPosting(POSTING_KEY);
//		} catch (PostingException e) {
//			fail();
//		}
//
//		assertNotNull(post);
//		assertEquals(PRICE, post.getPrice());
//		// NULL POINTER FOR NOW BECAUSE METHODS NOT YET IMPLEMENTED 
//		// (convert artwork and artist to Dto)
//		assertEquals(ARTWORK_KEY, post.getItem().getId() );
//		assertEquals(USERROLE_KEY, post.getItem().getArtist().getId());
//	}

	@Test
	public void testGetInvalidPosting() {
		PostingDto post = null;

		try {
			post = postingService.getPosting(INVALID_POSTING_KEY);
		} catch (PostingException e) {
			assertEquals(e.getMessage(), "Posting not found");
		}
		assertNull(post);
	}

	@Test
	public void testGetPostingWithoutCorrectOwner() {
		PostingDto post = null;
		try {
			post = postingService.getPostingOfArtwork(USERROLE_KEY2, ARTWORK_KEY);

		} catch (UserRoleException e) {
			assertEquals("Artist does not own artwork", e.getMessage());
		}
		assertNull(post);
	}

	@Test
	public void testGetPostingWithInvalidArtist() {
		PostingDto post = null;

		try {
			post = postingService.getPostingOfArtwork(INVALID_ARTIST_KEY, ARTWORK_KEY);
		} catch (UserRoleException e) {
			assertEquals("Artist does not exist", e.getMessage());
		}
		assertNull(post);
	}

	@Test
	public void testGetPostingWithInvalidArtwork() {
		PostingDto post = null;

		try {
			post = postingService.getPostingOfArtwork(USERROLE_KEY, INVALID_ARTWORK_KEY);

		} catch (ArtworkException e) {
			assertEquals("Artwork does not exist", e.getMessage());
		}
		assertNull(post);
	}

	@Test
	public void testGetAllPostingsByArtist() {
		Set<Posting> postings = null;

		try {
			postings = postingService.getAllPostingsByArtist(USERROLE_KEY);
		} catch (Exception e) {
			fail();
		}

		assertFalse(postings.isEmpty());
		assertTrue(postings.contains(POSTING));

	}
	
	@Test 
	public void testGetAllPostingsFromArtistWhoDoesntHavePostings() {
		Set<Posting> postings = null;
		
		try {
			postings = postingService.getAllPostingsByArtist(USERROLE_KEY2);
		} catch (Exception e) {
			fail();
		}
		
		assertTrue(postings.isEmpty());
	}
	



}