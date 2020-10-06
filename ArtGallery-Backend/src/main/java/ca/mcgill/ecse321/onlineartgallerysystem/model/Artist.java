import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Artist extends UserRole{
   private Set<Artwork> artworks;
   
   @OneToMany(mappedBy="creator" )
   public Set<Artwork> getArtworks() {
      return this.artworks;
   }
   
   public void setArtworks(Set<Artwork> artworkss) {
      this.artworks = artworkss;
   }
   
   private String biography;

public void setBiography(String value) {
    this.biography = value;
}
public String getBiography() {
    return this.biography;
}
}
