package ca.mcgill.ecse321.onlineartgallerysystem.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Profile{
   private OnlineArtGallerySystem system;

public void setSystem(OnlineArtGallerySystem value) {
    this.system = value;
}
public OnlineArtGallerySystem getSystem() {
    return this.system;
}
private String username;

public void setUsername(String value) {
    this.username = value;
}
public String getUsername() {
    return this.username;
}
private boolean isArtistProfile;

public void setIsArtistProfile(boolean value) {
    this.isArtistProfile = value;
}
public boolean isIsArtistProfile() {
    return this.isArtistProfile;
}
private String phoneNumber;

public void setPhoneNumber(String value) {
    this.phoneNumber = value;
}
public String getPhoneNumber() {
    return this.phoneNumber;
}
private String emailAddress;

public void setEmailAddress(String value) {
    this.emailAddress = value;
}
public String getEmailAddress() {
    return this.emailAddress;
}
   private User user;
   
   @OneToOne(optional=false)
   public User getUser() {
      return this.user;
   }
   
   public void setUser(User user) {
      this.user = user;
   }
   
   }