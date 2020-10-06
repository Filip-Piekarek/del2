import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public abstract class UserRole{
   private Person person;
   
   @OneToOne(optional=false)
   public Person getPerson() {
      return this.person;
   }
   
   public void setPerson(Person person) {
      this.person = person;
   }
   
   }
