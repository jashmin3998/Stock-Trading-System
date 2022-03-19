package com.stockTrading.stockTradingSystem.model;

        import javax.persistence.*;

@Entity
@Table(
        name="UserDtl",
        uniqueConstraints= @UniqueConstraint(columnNames={"username", "email"})
)
public class UserDtl {

    public int getUserid() {
        return Userid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Userid;
    private String firstname;
    private String lastname;

    private String username;

    private String email;
    private String pwd;
    @Enumerated
    private UserRole userRole;
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserDtl(){

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


}
