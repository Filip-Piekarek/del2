package ca.mcgill.ecse321.artgallery.model;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.sql.Date;

@Entity
@Table(name="artworks")
public class Artwork{
	
	@Id
    @Column(unique=true)
    private long id;

    public void setId(long value) {
        this.id = value;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return this.id;
    }
    
    private Artist creator;
    
    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    public Artist getCreator() {
        return this.creator;
    }

    public void setCreator(Artist creator) {
        this.creator = creator;
    }
    
    private Posting posting;

    @OneToOne(mappedBy="item" , optional=true)
    public Posting getPosting() {
        return this.posting;
    }

    public void setPosting(Posting posting) {
        this.posting = posting;
    }
    

    @Column
    private String name;

    public void setName(String value) {
        this.name = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Column
    private String description;

    public void setDescription(String value) {
        this.description = value;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    
    
    
    @Enumerated
    private ArtworkType artworkType;

    public void setArtworkType(ArtworkType value) {
        this.artworkType = value;
    }
    
    public ArtworkType getArtworkType() {
        return this.artworkType;
    }
    
    @Column
    private Date date;

    public void setDate(Date value) {
        this.date = value;
    }
    
    public Date getDate() {
        return this.date;
    }
}
