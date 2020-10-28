package ca.mcgill.ecse321.artgallery.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.artgallery.dao.AddressCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtworkCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.PostingCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.ProfileCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.UserCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.UserRoleCrudRepository;
import ca.mcgill.ecse321.artgallery.dto.AddressDto;
import ca.mcgill.ecse321.artgallery.dto.ArtistDto;
import ca.mcgill.ecse321.artgallery.dto.ArtworkDto;
import ca.mcgill.ecse321.artgallery.dto.ClientDto;
import ca.mcgill.ecse321.artgallery.dto.OrderDto;
import ca.mcgill.ecse321.artgallery.dto.ProfileDto;
import ca.mcgill.ecse321.artgallery.dto.UserDto;
import ca.mcgill.ecse321.artgallery.dto.UserRoleDto;
import ca.mcgill.ecse321.artgallery.model.Address;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Client;
import ca.mcgill.ecse321.artgallery.model.Order;
import ca.mcgill.ecse321.artgallery.model.Profile;
import ca.mcgill.ecse321.artgallery.model.User;
import ca.mcgill.ecse321.artgallery.model.UserRole;

@Service
public class ProfileService {
	
	@Autowired
	private UserCrudRepository userRepo;
	@Autowired 
	private UserRoleCrudRepository userRoleRepo;
	@Autowired
	private ProfileCrudRepository profileRepo;
	@Autowired
	private ArtworkCrudRepository artworkRepo;
	@Autowired
	private AddressCrudRepository addressRepo;
	@Autowired
	private PostingCrudRepository postingRepo;
	
	/**
	 * Converts a User entity object to a User data transfer object.
	 * 
	 * @param user
	 * @return 
	 */
	public UserDto convertUserToDto(User user) {
			
		UserDto userDto = new UserDto();
		userDto.setName(user.getName());
		userDto.setId(user.getId());
		if (user.getProfile() != null) {
			userDto.setProfile(convertProfileToDto(user.getProfile()));
		}
		if (user.getUserRole() != null) {
			userDto.setUserRole(convertUserRoleToDto(user.getUserRole()));
		}
		
		return userDto;
	}
	
	/**
	 * Converts a Profile entity object to a Profile data transfer object.
	 * 
	 * @param profile
	 * @return 
	 */
	public ProfileDto convertProfileToDto(Profile profile) {
		
		ProfileDto profileDto = new ProfileDto();
		profileDto.setEmailAddress(profile.getEmailAddress());
		profileDto.setIsArtistProfile(profile.isIsArtistProfile());
		profileDto.setPassword(profile.getPassword());
		profileDto.setPhoneNumber(profile.getPhoneNumber());
		if (profile.getUser() != null) {
			profileDto.setUser(convertUserToDto(profile.getUser()));
		}
		profileDto.setUsername(profile.getUsername());
		
		return profileDto;
	}
	
	/**
	 * Converts a UserRole entity object to a UserRole data transfer object.
	 * 
	 * @param userRole
	 * @return 
	 */
	public UserRoleDto convertUserRoleToDto(UserRole userRole) {
		UserRoleDto userRoleDto = new UserRoleDto();
		userRoleDto.setId(userRole.getId());
		userRoleDto.setUser(convertUserToDto(userRole.getUser()));
		
		return userRoleDto;
	}
	
	/**
	 * Converts a Client entity object to a Client data transfer object.
	 * 
	 * @param client
	 * @return 
	 */
	public ClientDto convertClientToDto(Client client) {
		
		ClientDto clientDto = new ClientDto();
		clientDto.setAddresses(convertAddressesToDto(client.getAddresses()));
		clientDto.setId(client.getId());
		clientDto.setOrders(convertOrdersToDto(client.getOrders()));
		
		return clientDto;
	}
	
	/**
	 * Converts a Set<Address> entity object to a Set<Address> data transfer object.
	 * 
	 * @param addresses
	 * @return 
	 */
	public Set<AddressDto> convertAddressesToDto(Set<Address> addresses){
		
		Set<AddressDto> addressesDto = new HashSet<AddressDto>();
		for (Address address : addresses) {
			addressesDto.add(convertAddressToDto(address));
		}
		
		return addressesDto;
	}
	
	/**
	 * Converts a Address entity object to a Address data transfer object.
	 * 
	 * @param address
	 * @return 
	 */
	public AddressDto convertAddressToDto(Address address) {
		
		AddressDto addressDto = new AddressDto();
		addressDto.setAppartmentNumber(address.getAppartmentNumber());
		addressDto.setCity(address.getCity());
		addressDto.setClient(convertClientToDto(address.getClient()));
		addressDto.setCountry(address.getCountry());
		addressDto.setId(address.getId());
		addressDto.setPostalCode(address.getPostalCode());
		addressDto.setProvince(address.getProvince());
		addressDto.setStreetName(address.getStreetName());
		addressDto.setStreetNumber(address.getStreetNumber());
		
		return addressDto;
	}
	
	/**
	 * Converts a Artist entity object to a Artist data transfer object.
	 * 
	 * @param artist
	 * @return 
	 */
	public ArtistDto convertArtistToDto(Artist artist) {
		
		ArtistDto artistDto = new ArtistDto();
		
		artistDto.setArtworks(convertArtworksToDto(artist.getArtworks()));
		artistDto.setBiography(artist.getBiography());
		artistDto.setId(artist.getId());
		
		return artistDto;
	}
	
	/**
	 * Converts a Artwork entity object to a Artwork data transfer object.
	 * 
	 * @param art
	 * @return 
	 */
	public ArtworkDto convertArtworkToDto(Artwork art) {
		// to implement in artwork service class
		return null;
	}
	
	/**
	 * Converts a Set<Order> entity object to a Set<Order> data transfer object.
	 * 
	 * @param orders
	 * @return 
	 */
	public Set<OrderDto> convertOrdersToDto(Set<Order> orders) {
		// to implement in order service class
		return null;
	}
	
	/**
	 * Converts a Set<Artwork> entity object to a Set<Artwork> data transfer object.
	 * 
	 * @param artworks
	 * @return
	 */
	public Set<ArtworkDto> convertArtworksToDto(Set<Artwork> artworks){
		
		Set<ArtworkDto> artworksDto = new HashSet<ArtworkDto>();
		for (Artwork artwork : artworks) {
			artworksDto.add(convertArtworkToDto(artwork));
		}
		
		return artworksDto;
	}
	
	/**
	 * 
	 * Create a User object.
	 * 
	 * @param name
	 * @return
	 */
	@Transactional
	public UserDto createUser(String name) {
			
		//Create user
		User user = new User();
		user.setName(name);
		
		//Save user in repository
		userRepo.save(user);
		
		return convertUserToDto(user);
	}
		
	/**
	 * 
	 * Returns a User.
	 * 	
	 * @param id
	 * @return
	 */
	@Transactional
	public UserDto getUser(long id) {
		User user = userRepo.findUserById(id);
		return convertUserToDto(user);
	}
	
	/**
	 * 
	 * Returns all users.
	 * 
	 * @return
	 */
	@Transactional
	public Set<UserDto> getAllUsers() {
		
		Set<UserDto> usersDto = new HashSet<UserDto>();
		for (User user : userRepo.findAll()) {
			usersDto.add(convertUserToDto(user));
		}
		return usersDto;
	}
	
	/**
	 * 
	 * Create Profile object.
	 * 
	 * @param userId
	 * @param username
	 * @param password
	 * @param email
	 * @param phone
	 * @param isArtist
	 * @return
	 */
	@Transactional
	public ProfileDto createProfile(long userId, String username, String password, String email, String phone, boolean isArtist) {
		
		//Create Profile
		Profile profile = new Profile();
		profile.setUser(userRepo.findUserById(userId));
		profile.setUsername(username);
		profile.setPassword(password);
		profile.setEmailAddress(email);
		profile.setPhoneNumber(phone);
		profile.setIsArtistProfile(isArtist);
		
		//Save profile in repository
		profileRepo.save(profile);
		
		return convertProfileToDto(profile);
	}
	
	/**
	 * 
	 * Returns all Artworks.
	 * 
	 * @return
	 */
	@Transactional
	public Set<ArtworkDto> getAllArtworks() {
		
		Set<ArtworkDto> artworksDto = new HashSet<ArtworkDto>();
		for (Artwork artwork : artworkRepo.findAll()) {
			artworksDto.add(convertArtworkToDto(artwork));
		}
		return artworksDto;
	}
	
	/**
	 * 
	 * Deletes Profile from repository.
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean deleteProfile(long id) {
		
		Profile profile = userRepo.findUserById(id).getProfile();
		long userRoleID = profile.getUser().getUserRole().getId();
		
		if(profile.isIsArtistProfile()) {
			for (Artwork art : ((Artist) profile.getUser().getUserRole()).getArtworks()) {
				artworkRepo.delete(art);
				art.setPosting(null);
				postingRepo.delete(art.getPosting());
			}
		}
		profileRepo.deleteById(profile.getUsername());
		userRoleRepo.deleteById(userRoleID);
		userRepo.deleteById(id);
		
		return true;
	}
	
	/**
	 * 
	 * Returns an updated Profile with updated attributes.
	 * 
	 * @param id
	 * @param newUsername
	 * @param newPassword
	 * @param newEmail
	 * @param newPhone
	 * @param newName
	 * @return
	 */
	@Transactional
	public ProfileDto editProfile(long id, String newUsername, String newPassword, String newEmail, String newPhone, String newName) {
		Profile profile = userRepo.findUserById(id).getProfile();
		User user = profile.getUser();
		profile.setEmailAddress(newEmail);
		profile.setPassword(newPassword);
		profile.setPhoneNumber(newPhone);
		profile.getUser().setName(newName);
		profile.setUsername(newUsername);
		
		profileRepo.save(profile);
		userRepo.save(user);
		
		return convertProfileToDto(profile);
	}
	
	/**
	 * 
	 * Returns a Profile.
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public ProfileDto getProfile(long id) {
		return convertProfileToDto(userRepo.findUserById(id).getProfile());
	}
	
	/**
	 * 
	 * Returns all Profiles.
	 * 
	 * @return
	 */
	@Transactional
	public Set<ProfileDto> getAllProfile() {
		
		Set<ProfileDto> profilesDto = new HashSet<ProfileDto>();
		for (Profile profile : profileRepo.findAll()) {
			profilesDto.add(convertProfileToDto(profile));
		}
		return profilesDto;
	}
	
	/**
	 * 
	 * Create Client object.
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional
	public ClientDto createClient(long userId) {
		
		//Create Client userRole
		Client client = new Client();
		Set<Address> addresses = new HashSet<Address>();
		client.setAddresses(addresses);
		client.setUser(userRepo.findUserById(userId));
		
		//Save client in repository
		userRoleRepo.save(client);
		
		return convertClientToDto(client);
	}
	
	/**
	 * 
	 * Create Address object
	 * 
	 * @param streetnumber
	 * @param streetname
	 * @param city
	 * @param apartmentNumber
	 * @param province
	 * @param country
	 * @param postalCode
	 * @param clientId
	 * @return
	 */
	@Transactional
	public AddressDto createAddress(int streetnumber, String streetname, String city, Integer apartmentNumber, String province, String country, String postalCode, long clientId) {
		
		Address address = new Address();
		address.setAppartmentNumber(apartmentNumber);
		address.setCity(city);
		address.setClient((Client)(userRoleRepo.findUserRoleById(clientId)));//maybe from username get client because yeeeeet
		address.setCountry(country);
		address.setPostalCode(postalCode);
		address.setProvince(province);
		address.setStreetName(streetname);
		address.setStreetNumber(streetnumber);
		
		addressRepo.save(address);
		
		return convertAddressToDto(address);
	}
	
	/**
	 * 
	 * Return an updated Address with updated attributes.
	 * 
	 * @param id
	 * @param streetnumber
	 * @param streetname
	 * @param city
	 * @param apartmentNumber
	 * @param province
	 * @param country
	 * @param postalCode
	 * @param clientId
	 * @return
	 */
	@Transactional
	public AddressDto editAddress(long id, int streetnumber, String streetname, String city, Integer apartmentNumber, String province, String country, String postalCode, long clientId) {
		
		Address address = addressRepo.findAddressById(id);
		
		address.setAppartmentNumber(apartmentNumber);
		address.setCity(city);
		address.setClient((Client)(userRoleRepo.findUserRoleById(clientId)));
		address.setCountry(country);
		address.setPostalCode(postalCode);
		address.setProvince(province);
		address.setStreetName(streetname);
		address.setStreetNumber(streetnumber);
		
		addressRepo.save(address);
		
		return convertAddressToDto(address);
	}
	
	/**
	 * 
	 * Deletes address from repository.
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean deleteAddress(long id) {
		
		Address address = addressRepo.findAddressById(id);
		addressRepo.delete(address);
		
		return true;
	}		
	
	/**
	 * 
	 * Return a Client.
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public ClientDto getClient(long id) {
		return convertClientToDto((Client) userRoleRepo.findUserRoleById(id));
	}
	
	/**
	 * 
	 * Returns all Clients.
	 * 
	 * @return
	 */
	@Transactional
	public Set<ClientDto> getAllClient() {
		
		Set<ClientDto> clientsDto = new HashSet<ClientDto>();
		for (Profile profile : profileRepo.findAll()) {
			if (!profile.isIsArtistProfile()) {
				clientsDto.add(convertClientToDto((Client) profile.getUser().getUserRole()));
			}
		}
		return clientsDto;
	}
	
	/**
	 * 
	 * Create Artist object.
	 * 
	 * @param id
	 * @param bio
	 * @return
	 */
	@Transactional
	public ArtistDto createArtist(long id, String bio) {
		
		// Create artist userRole
		Artist artist = new Artist();
		Set<Artwork> artworks = new HashSet<Artwork>();
		artist.setBiography(bio);
		artist.setUser(userRepo.findUserById(id));
		artist.setArtworks(artworks);
				
		// Save artist in repository
		userRoleRepo.save(artist);
		
		return convertArtistToDto(artist);
	}
	
	/**
	 * 
	 * Return an Artist.
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public ArtistDto getArtist(long id) {
		 Artist artist = (Artist) userRoleRepo.findUserRoleById(id);
		 return convertArtistToDto(artist);
	}
	
	/**
	 * 
	 * Returns all Artists.
	 * 
	 * @return
	 */
	@Transactional
	public Set<ArtistDto> getAllArtists() {
		Set<ArtistDto> artistsDto = new HashSet<ArtistDto>();
		for (Profile profile : profileRepo.findAll()) {
			if (profile.isIsArtistProfile()) {
				artistsDto.add(convertArtistToDto((Artist) profile.getUser().getUserRole()));
			}
		}
		return artistsDto;
	}
	
}
