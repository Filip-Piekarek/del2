package ca.mcgill.ecse321.onlineartgallerysystem.model;


import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
public class Inventory{
   private Set<User> users;
   
   @OneToMany(mappedBy="inventory" )
   public Set<User> getUsers() {
      return this.users;
   }
   
   public void setUsers(Set<User> userss) {
      this.users = userss;
   }
   
   private Set<Posting> postings;
   
   @OneToMany(mappedBy="inventory" , cascade={CascadeType.ALL})
   public Set<Posting> getPostings() {
      return this.postings;
   }
   
   public void setPostings(Set<Posting> postingss) {
      this.postings = postingss;
   }
}
