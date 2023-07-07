package entity;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    public static final long serialVersionUID_v1 = -2752716689505898006L;
    public static final long serialVersionUID_v2 = 4390163101870860443L;
    
    private static final long serialVersionUID = serialVersionUID_v2;

    private String username;
    private String password;
    private String fullname;

    public User() {
    }

    public User(String username, String password, String fullname) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, fullname);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        return Objects.equals(username, other.username) &&
                Objects.equals(password, other.password) &&
                Objects.equals(fullname, other.fullname);
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        // Handle version compatibility
        if (serialVersionUID == serialVersionUID_v1) {
            // Perform necessary conversions or transformations for version 1
        } else if (serialVersionUID == serialVersionUID_v2) {
            // Perform necessary conversions or transformations for version 2
        } else {
            throw new InvalidClassException("Unsupported version of User class");
        }
    }
}
