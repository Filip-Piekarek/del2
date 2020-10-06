package ca.mcgill.ecse321.onlineartgallerysystem.model;

import javax.persistence.Entity;
import Person;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import Profile;
import Posting;
import Artwork;

@Entity
public class OnlineArtGallerySystem{
   private Set<Person> users;
   
   @OneToMany(mappedBy="system" , cascade={CascadeType.ALL})
   public Set<Person> getUsers() {
      return this.users;
   }
   
   public void setUsers(Set<Person> userss) {
      this.users = userss;
   }
   
   private Set<Profile> profiles;
   
   @OneToMany(mappedBy="system" , cascade={CascadeType.ALL})
   public Set<Profile> getProfiles() {
      return this.profiles;
   }
   
   public void setProfiles(Set<Profile> profiless) {
      this.profiles = profiless;
   }
   
   private Set<Posting> postings;
   
   @OneToMany(mappedBy="system" , cascade={CascadeType.ALL})
   public Set<Posting> getPostings() {
      return this.postings;
   }
   
   public void setPostings(Set<Posting> postingss) {
      this.postings = postingss;
   }
   
   private Set<Artwork> artworks;
   
   @OneToMany(mappedBy="system" , cascade={CascadeType.ALL})
   public Set<Artwork> getArtworks() {
      return this.artworks;
   }
   
   public void setArtworks(Set<Artwork> artworkss) {
      this.artworks = artworkss;
   }
   
   }
