package ca.mcgill.ecse321.artgallery.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Artist extends UserRole{
    
    @Id
    @Column
    private long id;

    public void setId(long value) {
        this.id = value;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return this.id;
    }

    private Set<Artwork> artworks;

    @OneToMany(mappedBy="creator", cascade={CascadeType.ALL})
    public Set<Artwork> getArtworks() {
        return this.artworks;
    }

    public void setArtworks(Set<Artwork> artworkss) {
        this.artworks = artworkss;
    }
    
    public void addArtwork(Artwork artwork) {
    	this.artworks.add(artwork);
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
