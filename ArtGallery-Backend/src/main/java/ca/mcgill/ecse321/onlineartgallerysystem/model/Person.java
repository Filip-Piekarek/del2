import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

@Entity
public class Person{
   private OnlineArtGallerySystem system;

public void setSystem(OnlineArtGallerySystem value) {
    this.system = value;
}
public OnlineArtGallerySystem getSystem() {
    return this.system;
}
private Profile profile;

@OneToOne(mappedBy="profileOwner" , optional=false)
public Profile getProfile() {
   return this.profile;
}

public void setProfile(Profile profile) {
   this.profile = profile;
}

private UserRole userRole;

@OneToOne(mappedBy="person" , optional=false)
public UserRole getUserRole() {
   return this.userRole;
}

public void setUserRole(UserRole userRole) {
   this.userRole = userRole;
}

private Inventory ;

@ManyToOne(optional=false)
public Inventory get() {
   return this.;
}

public void set(Inventory ) {
   this. = ;
}

private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
}
private long id;

public void setId(long value) {
    this.id = value;
}
public long getId() {
    return this.id;
}
}
