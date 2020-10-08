package ca.mcgill.ecse321.onlineartgallerysystem.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class OnlineArtGallerySystem{
   private long id;

public void setId(long value) {
    this.id = value;
}
public long getId() {
    return this.id;
}
   private Set<User> users;
   
   @OneToMany(mappedBy="onlineArtGallerySystem" , cascade={CascadeType.ALL})
   public Set<User> getUsers() {
      return this.users;
   }
   
   public void setUsers(Set<User> userss) {
      this.users = userss;
   }
   
   }
