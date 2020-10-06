import javax.persistence.Entity;
import javax.persistence.OneToOne;
import ca.mcgill.ecse321.onlineartgallerysystem.model.OnlineArtGallerySystem;
import javax.persistence.ManyToOne;

@Entity
public class Profile{
   private Person profileOwner;
   
   @OneToOne(optional=false)
   public Person getProfileOwner() {
      return this.profileOwner;
   }
   
   public void setProfileOwner(Person profileOwner) {
      this.profileOwner = profileOwner;
   }
   
   private OnlineArtGallerySystem system;
   
   @ManyToOne(optional=false)
   public OnlineArtGallerySystem getSystem() {
      return this.system;
   }
   
   public void setSystem(OnlineArtGallerySystem system) {
      this.system = system;
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
}
