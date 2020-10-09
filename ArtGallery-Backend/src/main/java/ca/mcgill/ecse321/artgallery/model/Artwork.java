package ca.mcgill.ecse321.artgallery.model;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.sql.Date;

@Entity
public class Artwork{
	
	private Artist creator;
	
	@OneToOne(optional=false)
	public Artist getCreator() {
		return this.creator;
	}

	public void setCreator(Artist creator) {
		this.creator = creator;
	}
	
	private Posting posting;

	@OneToOne(mappedBy="item" , optional=false)
	public Posting getPosting() {
		return this.posting;
	}

	public void setPosting(Posting posting) {
		this.posting = posting;
	}
	

	private String name;

	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	
	private String description;

	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	
	@Id
	private long id;

	public void setId(long value) {
		this.id = value;
	}
	
	@Id
	public long getId() {
		return this.id;
	}
	
	
	@Enumerated
	private ArtworkType artworkType;

	public void setArtworkType(ArtworkType value) {
		this.artworkType = value;
	}
	
	public ArtworkType getArtworkType() {
		return this.artworkType;
	}
	
	
	private Date date;

	public void setDate(Date value) {
		this.date = value;
	}
	
	public Date getDate() {
		return this.date;
	}
}
