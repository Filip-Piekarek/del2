import javax.persistence.Entity;
import java.util.Set;

@Entity
public class OnlineArtGallerySystem{
   private Set<Person> users;

public void setUsers(Set<Person> value) {
    this.users = value;
}
public Set<Person> getUsers() {
    return this.users;
}
private Set<Profile> profiles;

public void setProfiles(Set<Profile> value) {
    this.profiles = value;
}
public Set<Profile> getProfiles() {
    return this.profiles;
}
private Set<Posting> postings;

public void setPostings(Set<Posting> value) {
    this.postings = value;
}
public Set<Posting> getPostings() {
    return this.postings;
}
private Set<Artwork> artworks;

public void setArtworks(Set<Artwork> value) {
    this.artworks = value;
}
public Set<Artwork> getArtworks() {
    return this.artworks;
}
}
