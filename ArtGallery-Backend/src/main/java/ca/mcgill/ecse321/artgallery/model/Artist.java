package ca.mcgill.ecse321.artgallery.model;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="artists")
public class Artist extends UserRole{
	
	@Id
	@Column(unique=true)
	private long id;

	public void setId(long value) {
		this.id = value;
	}
	@Id
	public long getId() {
		return this.id;
	}

	private Set<Artwork> artworks;

	@OneToMany(mappedBy="creator" )
	public Set<Artwork> getArtworks() {
		return this.artworks;
	}

	public void setArtworks(Set<Artwork> artworkss) {
		this.artworks = artworkss;
	}

	@Column
	private String biography;

	public void setBiography(String value) {
		this.biography = value;
	}
	
	public String getBiography() {
		return this.biography;
	}
}
