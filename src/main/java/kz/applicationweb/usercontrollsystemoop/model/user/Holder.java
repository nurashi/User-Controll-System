package kz.applicationweb.usercontrollsystemoop.model.user;

public class Holder extends User {

    private static final String DEFAULT_NAME = "John Doe";
    private static final String DEFAULT_EMAIL = "john.doe@example.com";

    public Holder() {
    }
    public Holder(String name, String email) {
        setName(name);
        setEmail(email);
    }
    public String getName() {
        return DEFAULT_NAME;
    }
    public String getEmail() {
        return DEFAULT_EMAIL;
    }
    public void setName(String name) {
        setName(name);
    }
    public void setEmail(String email) {
        setEmail(email);
    }
    public boolean isHolder(){
        return true;
    }
}

