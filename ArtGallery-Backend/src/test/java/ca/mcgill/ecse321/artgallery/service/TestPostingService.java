package ca.mcgill.ecse321.artgallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
	
		// Posting parameters
	private static final long POSTING_KEY = 0;
	private static final double PRICE = 5000;
	private static final boolean VISIBILITY = true;
	private static final boolean IS_ONLINE = true;
	private static final int PRIORITY = 0;
	Posting POSTING = new Posting();
	
			
	private void setters(boolean artistSetUp, boolean artworkSetUp, boolean postingSetUp) {
		
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
		
		if (artistSetUp) {
			Set<Artwork> artworks = new HashSet<Artwork>();
			ARTIST.setId(USERROLE_KEY);
			ARTIST.setBiography(BIO);
			ARTIST.setArtworks(artworks);
			ARTIST.setUser(USER);
		}
		
		if (artworkSetUp) {
			ARTWORK.setArtworkType(TYPE);
			ARTWORK.setDate(DATE);
			ARTWORK.setDescription(DESCRIPTION);
			ARTWORK.setName(ARTWORK_NAME);
			ARTWORK.setId(ARTWORK_KEY);
			ARTWORK.setCreator(ARTIST);
			ARTIST.addArtwork(ARTWORK);
		}
		
		if (postingSetUp) {
			POSTING.setId(POSTING_KEY);
			POSTING.setIsOnline(IS_ONLINE);
			POSTING.setPrice(PRICE);
			POSTING.setPriority(PRIORITY);
			POSTING.setVisibility(VISIBILITY);
			POSTING.setItem(ARTWORK);
			ARTWORK.setPosting(POSTING);
		}
		
	}
	
	@BeforeEach
	public void setMockOutput() {

		lenient().when(userRoleDao.findUserRoleById(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(USERROLE_KEY)) {
				
				setters(true,false,false);
				
				return ARTIST;
				
			} else {
				return null;
			}

		});
		
		lenient().when(artworkDao.findArtworkById(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(ARTWORK_KEY)) {
				
				setters(true, true,false);
			
				return ARTWORK;
				
			} else {
				return null;
			}
		});

		lenient().when(postingDao.findPostingById(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(POSTING_KEY)) {
				
				setters(true,true, true);
	
				return POSTING;
				
			} else {
				return null;
			}
		});
		
		lenient().when(postingService.getAllPostingsDto()).thenAnswer( (InvocationOnMock invocation) -> {
			
				setters(true,true,true);
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
		assertEquals(5000, post.getPrice());

	}


}