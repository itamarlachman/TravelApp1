package il.co.expertize.models;

import java.io.Serializable;

public class Customer implements Serializable  {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String address;
    protected String tel;

    public Customer() {
    }


    public Customer(String firstName, String lastName, String email, String address, String tel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.tel = tel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFullName() {
        return String.format("%s %s",firstName,lastName);
    }

    @Override
    public String toString() {
        return  "firstName='" + firstName + ", lastName='" + lastName ;
    }
}
