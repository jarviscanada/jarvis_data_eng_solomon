package ca.jrvs.apps.trading.model.domain;
import java.sql.Date;

public class Trader implements Entity<Integer> {

  private Integer id = 0;
  private String firstName = null;
  private String lastName = null;
  private Date dob = new Date(System.currentTimeMillis());
  private String country = null;
  private String email = null;

  @Override
  public Integer getId () {
    return id;
  }
  
  @Override
  public void setID (Integer integer) {
    this.id = integer;
  }
  
  public String getFirstName () {
    return firstName;
  }
  
  public void setFirstName (String firstName) {
    this.firstName = firstName;
  }
  
  public String getLastName () {
    return lastName;
  }
  
  public void setLastName (String lastName) {
    this.lastName = lastName;
  }
  
  public Date getDob () {
    return dob;
  }
  
  public void setDob (Date dob) {
    this.dob = dob;
  }
  
  public String getCountry () {
    return country;
  }
  
  public void setCountry (String country) {
    this.country = country;
  }
  
  public String getEmail () {
    return email;
  }
  
  public void setEmail (String email) {
    this.email = email;
  }
  
}
