package ca.mcgill.ecse321.onlineartgallerysystem.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public abstract class UserRole{
   private User user;
   
   @OneToOne(optional=false)
   public User getUser() {
      return this.user;
   }
   
   public void setUser(User user) {
      this.user = user;
   }
   
   }
