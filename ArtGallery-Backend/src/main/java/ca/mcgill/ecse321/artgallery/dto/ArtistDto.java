package ca.mcgill.ecse321.artgallery.dto;

import java.util.Collections;
import java.util.Set;

public class ArtistDto {

	private long id;
	private Set<ArtworkDto> artworks;
	private String biography;
	
	public ArtistDto() {
	}
	
	public ArtistDto(long id, String biography) {
		this(id, Collections.emptySet(), biography);
	}
	
	public ArtistDto(long id, Set<ArtworkDto> artworks, String biography) {
		this.id = id;
		this.artworks = artworks;
		this.biography = biography;
	}
	public long getId() {
        return id;
    }
	
	public void setId(long id) {
		this.id = id;
	}
	
	
}
