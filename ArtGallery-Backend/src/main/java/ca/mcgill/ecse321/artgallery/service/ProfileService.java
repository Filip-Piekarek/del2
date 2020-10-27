package ca.mcgill.ecse321.artgallery.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.artgallery.dao.AddressCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtworkCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.ProfileCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.UserCrudRepository;
import ca.mcgill.ecse321.artgallery.dao.UserRoleCrudRepository;
import ca.mcgill.ecse321.artgallery.model.Address;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Client;
import ca.mcgill.ecse321.artgallery.model.Profile;
import ca.mcgill.ecse321.artgallery.model.User;

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

	
	//Iterable to Set
		private <T> Set<T> toSet(Iterable<T> iterable){
			Set<T> resultList = new HashSet<T>();
			for (T t : iterable) {
				resultList.add(t);
			}
			return resultList;
		}
	
	//Service methods for User
		@Transactional
		public User createUser(String name) {
			
			//Create user
			User user = new User();
			user.setName(name);
			
			//Save user in repository
			userRepo.save(user);
			return user;
		}
		
		@Transactional
		public User getUser(long id) {
			User user = userRepo.findUserById(id);
			return user;
		}
		
		@Transactional
		public Set<User> getAllUsers() {
			return toSet(userRepo.findAll());
		}
		
		//Service methods for profile
		@Transactional
		public Profile createProfile(User user, String username, String password, String email, String phone, boolean isArtist) {
			
			//Create Profile
			Profile profile = new Profile();
			profile.setUser(user);
			profile.setUsername(username);
			profile.setPassword(password);
			profile.setEmailAddress(email);
			profile.setPhoneNumber(phone);
			profile.setIsArtistProfile(isArtist);
			
			//Save profile in repository
			profileRepo.save(profile);
			
			return profile;
		}
		@Transactional
		public Set<Artwork> getAllArtworks() {
			
			return toSet(artworkRepo.findAll());
		}
		
		@Transactional
		public void deleteProfile(Profile profile) {
			
			long id = profile.getUser().getId();
			long userRoleID = profile.getUser().getUserRole().getId();
			String username = profile.getUsername();
			profileRepo.deleteById(username);
			if(profile.isIsArtistProfile()) {
				for (Artwork art : ((Artist) profile.getUser().getUserRole()).getArtworks()) {
					artworkRepo.delete(art);
				}
			}
			userRoleRepo.deleteById(userRoleID);
			userRepo.deleteById(id);
		}
		
		@Transactional
		public Profile editProfile(String oldUsername, String newUsername, String newPassword, String newEmail, String newPhone,String newName) {
			Profile profile = profileRepo.findProfileByUsername(oldUsername);
			User user = profile.getUser();
			profile.setEmailAddress(newEmail);
			profile.setPassword(newPassword);
			profile.setPhoneNumber(newPhone);
			profile.getUser().setName(newName);
			profile.setUsername(newUsername);
			
			profileRepo.save(profile);
			userRepo.save(user);
			
			return profile;
		}
		
		@Transactional
		public Profile getProfile(String username) {
			return profileRepo.findProfileByUsername(username);
		}
		
		@Transactional
		public Set<Profile> getAllProfile() {
			return toSet(profileRepo.findAll());
		}
		
		//Service methods for Client
		@Transactional
		public Client createClient(User user) {
			
			//Create Client userRole
			Client client = new Client();
			Set<Address> addresses = new HashSet<Address>();
			client.setAddresses(addresses);
			client.setUser(user);
			
			//Save client in repository
			userRoleRepo.save(client);
			
			return client;
		}
		
		@Transactional
		public Address createAddress(int streetnumber, String streetname, String city, Integer apartmentNumber, String province, String country, String postalCode, Client client) {//frontend is the client as input for client-Eric
			
			Address address = new Address();
			address.setAppartmentNumber(apartmentNumber);
			address.setCity(city);
			address.setClient(client);//maybe from username get client because yeeeeet
			address.setCountry(country);
			address.setPostalCode(postalCode);
			address.setProvince(province);
			address.setStreetName(streetname);
			address.setStreetNumber(streetnumber);
			
			addressRepo.save(address);
			
			return address;
		}
		@Transactional
		public Address editAddress(Address address, int streetnumber, String streetname, String city, Integer apartmentNumber, String province, String country, String postalCode, Client client) {//frontend button adress-Eric
			
			address.setAppartmentNumber(apartmentNumber);
			address.setCity(city);
			address.setClient(client);//maybe from username get client because yeeeeet
			address.setCountry(country);
			address.setPostalCode(postalCode);
			address.setProvince(province);
			address.setStreetName(streetname);
			address.setStreetNumber(streetnumber);
			
			addressRepo.save(address);
			
			return address;
		}
		
		@Transactional
		public void deleteAddress(Address address) {//frontend button adress-Eric
			
			addressRepo.delete(address);
			
	
		}		
		
		
		
		@Transactional
		public Client getClient(long id) {
			return (Client) userRoleRepo.findUserRoleById(id);
		}
		
		@Transactional
		public Set<Client> getAllClient() {
			
			Set<Client> clients = new HashSet<Client>();
			for (Profile profile : profileRepo.findAll()) {
				if (!profile.isIsArtistProfile()) {
					clients.add((Client) profile.getUser().getUserRole());
				}
			}
			return clients;
		}
		
		//Service methods for Artist
		@Transactional
		public Artist createArtist(User user, String bio) {
			
			// Create artist userRole
			Artist artist = new Artist();
			Set<Artwork> artworks = new HashSet<Artwork>();
			artist.setBiography(bio);
			artist.setUser(user);
			artist.setArtworks(artworks);
					
			// Save artist in repository
			userRoleRepo.save(artist);
			
			return artist;
		}
		
		@Transactional
		public Artist getArtist(long id) {
			 Artist artist = (Artist) userRoleRepo.findUserRoleById(id);
			 return artist;
		}
		
		@Transactional
		public Set<Artist> getAllArtists() {
			Set<Artist> artists = new HashSet<Artist>();
			for (Profile profile : profileRepo.findAll()) {
				if (profile.isIsArtistProfile()) {
					artists.add((Artist) profile.getUser().getUserRole());
				}
			}
			return artists;
		}
		

}
