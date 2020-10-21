package ca.mcgill.ecse321.artgallery.dto;

public class ArtworkDto {

	private long id;
	private ArtistDto creator;
	private PostingDto posting;
	private String name;
	private String description;
	
	public ArtworkDto() {
	}
	
	public ArtworkDto(long id, ArtistDto creator, PostingDto posting, String name, String description) {
		this.id = id;
		this.creator = creator;
		this.posting = posting;
		this.name = name;
		this.description = description;
	}
	
	public long getId() {
		return id;
	}
	
	public ArtistDto getArtist() {
		return creator;
	}
	
	public void setArtist(ArtistDto creator) {
		this.creator = creator;
	}
	
	public PostingDto getPosting() {
		return posting;
	}
	
	public void setPosting(PostingDto posting) {
		this.posting = posting;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
