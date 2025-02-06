package kz.applicationweb.usercontrollsystemoop.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends User {
    public Admin() {

    }

    public Admin(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }
}
