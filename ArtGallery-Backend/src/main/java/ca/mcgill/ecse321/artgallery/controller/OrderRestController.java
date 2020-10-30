package ca.mcgill.ecse321.artgallery.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.mcgill.ecse321.artgallery.dto.AddressDto;
import ca.mcgill.ecse321.artgallery.dto.ArtistDto;
import ca.mcgill.ecse321.artgallery.dto.ArtworkDto;
import ca.mcgill.ecse321.artgallery.dto.ClientDto;
import ca.mcgill.ecse321.artgallery.dto.OrderDto;
import ca.mcgill.ecse321.artgallery.dto.PostingDto;
import ca.mcgill.ecse321.artgallery.model.Address;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Client;
import ca.mcgill.ecse321.artgallery.model.Order;
import ca.mcgill.ecse321.artgallery.model.OrderStatus;
import ca.mcgill.ecse321.artgallery.model.Posting;
import ca.mcgill.ecse321.artgallery.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
public class OrderRestController {
	
	@Autowired
	private OrderService orderService;
	
	//Probably gonna need to fix mapping things later
	
	@GetMapping(value = { "/orders/{orderID}", "/orders/{orderID}/" })
	public OrderDto getOrderByID(@PathVariable("orderID") long orderID) {
		
		return convertOrderToDto(orderService.getOrder(orderID));		
	}
	
	@GetMapping(value = { "/orders", "/orders/" })
	public Set<OrderDto> getAllOrders() {		
		
		return convertOrderSetToDto(orderService.getAllOrders());
	}
		
	@PostMapping(value = { "/orders/{orderID}", "/orders/{orderID}/" })
	public OrderDto createOrder(@PathVariable("clientID") long clientID) {
		
		return convertOrderToDto(orderService.createOrder(clientID));
	}
	
	@GetMapping(value = { "/orders/{orderID}", " /orders/{orderID}/" })
	public OrderDto deleteOrder(@PathVariable("orderID") long orderID) {
		
		return convertOrderToDto(orderService.deleteOrder(orderID));
	}
	
	@PostMapping(value = { "/orders/{orderID}", "/orders/{orderID}/" })
	public OrderDto addToOrder(@PathVariable("orderID") long orderID, @PathVariable("postingID") long postingID) {
		
		return convertOrderToDto(orderService.addToOrder(orderID, postingID));
	}
	
	@GetMapping(value = { "/orders/{orderID}", "/orders/{orderID}/" })
	public OrderDto removeFromOrder(@PathVariable("orderID") long orderID, @PathVariable("postingID") long postingID) {
		
		return convertOrderToDto(orderService.removeFromOrder(orderID, postingID));
	}
	
	@PostMapping(value = { "/orders/{orderID}", "/orders/{orderID}/" })
	public OrderDto setInStorePickup(@PathVariable("orderID") long orderID, @RequestParam boolean isInStorePickup) {
		
		return convertOrderToDto(orderService.setInStorePickup(orderID, isInStorePickup));
	}
	@PostMapping(value = { "/orders/{orderID}", "/orders/{orderID}/" })
	public OrderDto setOrderStatus(@PathVariable("orderID") long orderID, @PathVariable OrderStatus orderStatus) {
		
		return convertOrderToDto(orderService.setOrderStatus(orderID, orderStatus));
	}
	
	
	
	
		
	//Start of DTO conversions
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
	
	public Set<AddressDto> convertAddressSetToDto(Set<Address> addresses) {
		Set<AddressDto> addressesDto = new HashSet<AddressDto>();
		for (Address address : addresses) {
			addressesDto.add(convertAddressToDto(address));
		}
		
		return addressesDto;		
	}
	
	public ClientDto convertClientToDto(Client client) {
		ClientDto clientDto = new ClientDto();		
		clientDto.setAddresses(convertAddressSetToDto(client.getAddresses()));
		clientDto.setId(client.getId());
		clientDto.setOrders(convertOrderSetToDto(client.getOrders()));
		
		return clientDto;		
	}
	
	public Set<PostingDto> convertPostingSetToDto(Set<Posting> postings) {
		Set<PostingDto> postingsDto = new HashSet<PostingDto>();
		for (Posting posting : postings) {
			postingsDto.add(convertPostingToDto(posting));
		}
		return postingsDto;
	}
	
	public PostingDto convertPostingToDto(Posting posting) {
		PostingDto postingDto = new PostingDto();
		postingDto.setId(posting.getId());
		postingDto.setItem(convertArtworkToDto(posting.getItem()));
		postingDto.setOrder(convertOrderToDto(posting.getOrder()));
		postingDto.setPrice(posting.getPrice());
		postingDto.setPriority(posting.getPriority());
		postingDto.setVisibility(posting.isVisibility());
		
		return postingDto;	
	}
	
	public Set<ArtworkDto> convertArtworkSetToDto(Set<Artwork> artworks) {
		Set<ArtworkDto> artworksDto = new HashSet<ArtworkDto>();
		for (Artwork artwork : artworks) {
			artworksDto.add(convertArtworkToDto(artwork));
		}
		return artworksDto;
	}
	
	public ArtworkDto convertArtworkToDto(Artwork item) {
		ArtworkDto itemDto = new ArtworkDto();
		itemDto.setArtist(convertArtistToDto(item.getCreator()));
		itemDto.setDescription(item.getDescription());
		itemDto.setId(item.getId());
		itemDto.setName(item.getName());
		itemDto.setPosting(convertPostingToDto(item.getPosting()));
		
		return itemDto;		
	}
	public ArtistDto convertArtistToDto(Artist artist) {
		ArtistDto artistDto = new ArtistDto();
		artistDto.setArtworks(convertArtworkSetToDto(artist.getArtworks()));
		artistDto.setBiography(artist.getBiography());
		artistDto.setId(artist.getId());
		
		return artistDto;
	}
	
	public OrderDto convertOrderToDto(Order order) {
		
		OrderDto orderDto = new OrderDto();
		orderDto.setClient(convertClientToDto(order.getClient()));
		orderDto.setId(order.getId());
		orderDto.setInStorePickUp(order.isInStorePickUp());
		orderDto.setItems(convertPostingSetToDto(order.getItems()));
		orderDto.setOrderStatus(order.getOrderStatus());
		
		return orderDto;		
	}
	
	public Set<OrderDto> convertOrderSetToDto(Set<Order> orders) {
		Set<OrderDto> ordersDto = new HashSet<OrderDto>();
		for (Order order : orders) {
			ordersDto.add(convertOrderToDto(order));
		}
		return ordersDto;
	}
	//End of DTO conversions
}