package il.co.expertize.models;

import java.io.Serializable;

public class Company extends  Customer implements Serializable {

    public Company(String firstName, String lastName, String email, String address, String tel) {
        super(firstName, lastName, email, address, tel);
    }
}
