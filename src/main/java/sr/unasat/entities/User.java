package sr.unasat.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private int userID;
    
    @Column(name = "userFullname")
    private String userName;
    
    @Column(name = "userEmail")
    private String userEmail;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;

    public User() {}

    public User(String userName, String userEmail, String username, String password) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.username = username;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    @Override
    public String toString() {
        return "User [userID=" + userID + ", userName=" + userName + ", userEmail=" + userEmail + ", username=" + username
                + ", password=" + password + "]";
    }
}
