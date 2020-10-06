import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Inventory{
   private Set<Posting> items;
   
   @OneToMany(mappedBy="" )
   public Set<Posting> getItems() {
      return this.items;
   }
   
   public void setItems(Set<Posting> itemss) {
      this.items = itemss;
   }
   
   private Set<Person> users;
   
   @OneToMany(mappedBy="" )
   public Set<Person> getUsers() {
      return this.users;
   }
   
   public void setUsers(Set<Person> userss) {
      this.users = userss;
   }
   
   }
