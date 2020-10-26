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
	
	public Set<ArtworkDto> getArtworks() {
		return artworks;
	}
	
	public void setArtworks(Set<ArtworkDto> artworks) {
		this.artworks = artworks;
	}
	
	public void addArtwork(ArtworkDto artwork) {
		artworks.add(artwork);
	}
	
	public ArtworkDto getArtworkById(long id) {
		for (ArtworkDto art : this.artworks) {
			if (art.getId() == id) {
				return art;
			}
		}
		return null;
	}
	
	public void removeArtworkById(long id) {
		for (ArtworkDto art : this.artworks) {
			if (art.getId() == id) {
				this.artworks.remove(art);
			}
		}
	}
	
	public String getBiography() {
		return biography;
	}
	
	public void setBiography(String biography) {
		this.biography = biography;
	}
}
