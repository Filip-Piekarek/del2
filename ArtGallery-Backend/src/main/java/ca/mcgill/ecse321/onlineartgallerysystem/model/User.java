package ca.mcgill.ecse321.onlineartgallerysystem.model;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;


import javax.persistence.ManyToOne;

@Entity
public class User{
   private OnlineArtGallerySystem system;

public void setSystem(OnlineArtGallerySystem value) {
    this.system = value;
}
public OnlineArtGallerySystem getSystem() {
    return this.system;
}
private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
}
@Id
private long id;

public void setId(long value) {
this.id = value;
}
@Id
public long getId() {
return this.id;
}
   private UserRole userRole;
   
   @OneToOne(mappedBy="user" , cascade={CascadeType.ALL})
   public UserRole getUserRole() {
      return this.userRole;
   }
   
   public void setUserRole(UserRole userRole) {
      this.userRole = userRole;
   }
   
   private Profile profile;
   
   @OneToOne(mappedBy="user" , cascade={CascadeType.ALL})
   public Profile getProfile() {
      return this.profile;
   }
   
   public void setProfile(Profile profile) {
      this.profile = profile;
   }
   
   private Inventory inventory;
   
   @ManyToOne(optional=false)
   public Inventory getInventory() {
      return this.inventory;
   }
   
   public void setInventory(Inventory inventory) {
      this.inventory = inventory;
   }
   
   private OnlineArtGallerySystem onlineArtGallerySystem;
   
   @ManyToOne(optional=false)
   public OnlineArtGallerySystem getOnlineArtGallerySystem() {
      return this.onlineArtGallerySystem;
   }
   
   public void setOnlineArtGallerySystem(OnlineArtGallerySystem onlineArtGallerySystem) {
      this.onlineArtGallerySystem = onlineArtGallerySystem;
   }
   
   }
